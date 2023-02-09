package com.lfgtavora.pokecards.feature.set.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lfgtavora.pokecards.feature.set.data.domain.CardSet
import com.lfgtavora.pokecards.feature.set.data.response.CardSetDto
import com.lfgtavora.pokecards.feature.set.data.repository.CardSetRepository
import com.lfgtavora.pokecards.feature.set.domain.usecase.GetAllSetsByDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetViewModel @Inject constructor(
    getAllSetsByDateUseCase: GetAllSetsByDateUseCase,
) : ViewModel() {

    val uiState: StateFlow<FeedUiState> =
        getAllSetsByDateUseCase()
            .map(FeedUiState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = FeedUiState.Loading,
            )

    fun navigateToSetDetails() {
        viewModelScope.launch {

        }
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 16
    }

}

sealed interface FeedUiState {
    data class Success(val cardSets: List<CardSet>) : FeedUiState
    object Error : FeedUiState
    object Loading : FeedUiState
}
