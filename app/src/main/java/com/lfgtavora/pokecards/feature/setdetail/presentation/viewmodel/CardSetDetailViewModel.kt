package com.lfgtavora.pokecards.feature.setdetail.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.lfgtavora.pokecards.data.local.AppDatabase
import com.lfgtavora.pokecards.feature.set.data.CardRemoteMediator
import com.lfgtavora.pokecards.feature.set.data.domain.Card
import com.lfgtavora.pokecards.feature.set.data.repository.CardSetRepository
import com.lfgtavora.pokecards.feature.set.data.response.CardDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CardSetDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    cardSetRepository: CardSetRepository,
    appDatabase: AppDatabase
) : ViewModel() {

    private val setId: String = checkNotNull(savedStateHandle["id"])

    @OptIn(ExperimentalPagingApi::class)
    val paginateCards = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            initialLoadSize = PAGE_SIZE,
        ),
        pagingSourceFactory = {
            appDatabase.cardDao.pagingSource()
        },
        remoteMediator = CardRemoteMediator(
            setId = setId,
            cardRepository = cardSetRepository,
        )
    )
        .flow
        .cachedIn(viewModelScope)

    companion object {
        private const val PAGE_SIZE = 32
        private const val PREFETCH_DISTANCE = 10
    }

}

sealed interface SetDetailUiState {
    data class Success(val cards: MutableList<CardDto>) : SetDetailUiState
    object Error : SetDetailUiState
    object Loading : SetDetailUiState
}