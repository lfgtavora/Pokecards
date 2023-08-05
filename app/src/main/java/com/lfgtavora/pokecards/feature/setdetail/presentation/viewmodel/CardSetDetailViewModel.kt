package com.lfgtavora.pokecards.feature.setdetail.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lfgtavora.pokecards.feature.set.data.domain.Card
import com.lfgtavora.pokecards.feature.set.data.repository.CardSetRepository
import com.lfgtavora.pokecards.pagination.DefaultPaginator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardSetDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    cardSetRepository: CardSetRepository,
) : ViewModel() {

    private var setId: String = checkNotNull(savedStateHandle["id"])
    private var page = 1
    private var paginationReachedEnd = false

    private var _uiState: MutableState<SetDetailUiState> = mutableStateOf(SetDetailUiState.Loading)
    val uiState: State<SetDetailUiState> = _uiState

    private val paginator = DefaultPaginator(
        initialKey = page,
        onLoadUpdated = {
            if (page != 1) {
                _uiState.value =
                    (_uiState.value as SetDetailUiState.Success).copy(isPaginating = paginationReachedEnd.not())
                        .copy()

            }
        },
        onRequest = { _ ->
            cardSetRepository.paginateCards(setId, page, PAGE_SIZE)
        },
        getNextKey = {
            page++
            page
        },
        onError = { error ->
            _uiState.value = SetDetailUiState.Error(error?.localizedMessage)
        },
        onSuccess = { result, _ ->
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
                _uiState.value = (_uiState.value as SetDetailUiState.Success).copy(
                    cards = updatedCards,
                    isPaginating = false
                )

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

    fun setCardPreview(id: String?) {
        _uiState.value = (_uiState.value as SetDetailUiState.Success).copy(cardPreviewId = id)
    }

    companion object {
        private const val PAGE_SIZE = 32
    }

}

sealed interface SetDetailUiState {
    object Loading : SetDetailUiState
    data class Error(val message: String?, val page: Int = 1) : SetDetailUiState
    data class Success(
        val cards: MutableList<Card>,
        val isPaginating: Boolean = false,
        val isPaginateError: Boolean = false,
        val cardPreviewId: String? = null
    ) : SetDetailUiState
}