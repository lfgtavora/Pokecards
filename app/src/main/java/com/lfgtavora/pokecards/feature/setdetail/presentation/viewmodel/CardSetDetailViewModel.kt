package com.lfgtavora.pokecards.feature.setdetail.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lfgtavora.pokecards.feature.set.data.repository.CardSetRepository
import com.lfgtavora.pokecards.feature.set.data.response.CardDto
import com.lfgtavora.pokecards.pagination.DefaultPaginator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardSetDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    cardSetRepository: CardSetRepository,
) : ViewModel() {

    private var setId: String = checkNotNull(savedStateHandle["id"])
    private var page = 1
    var paginationReachedEnd = false

    private var _uiState: MutableState<SetDetailUiState> = mutableStateOf(SetDetailUiState.Loading)
    val uiState = _uiState

    private val paginator = DefaultPaginator(
        initialKey = page,
        onLoadUpdated = {
            if (page != 1) {
                _uiState.value =
                    (_uiState.value as SetDetailUiState.Success).copy(isPaginating = true).copy()

            }
        },
        onRequest = { nextPage ->
            cardSetRepository.paginateCards(setId, page, PAGE_SIZE)
        },
        getNextKey = {
            page++
            page
        },
        onError = { error ->
            _uiState.value = SetDetailUiState.Error(error?.localizedMessage)
        },
        onSuccess = { result, newKey ->
            if (page == 1)
                _uiState.value =
                    SetDetailUiState.Success(result.data.toMutableList(), isPaginating = false)
                        .copy()
            else {
                val updatedCards = (_uiState.value as SetDetailUiState.Success).copy().cards.apply {
                    addAll(
                        result.data
                    )
                }
                _uiState.value =
                    (_uiState.value as SetDetailUiState.Success).copy( cards = updatedCards,
                        isPaginating = false)

            }
            paginationReachedEnd = result.count < result.pageSize

        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        if (paginationReachedEnd.not()) {
            viewModelScope.launch {
                paginator.loadNextItems()
            }
        }
    }

    companion object {
        private const val PAGE_SIZE = 32
        private const val PREFETCH_DISTANCE = 10
    }

}

sealed interface SetDetailUiState {
    object Loading : SetDetailUiState
    data class Error(val message: String?, val page: Int = 1) : SetDetailUiState
    data class Success(
        val cards: MutableList<CardDto>,
        var isPaginating: Boolean = false
    ) : SetDetailUiState
}