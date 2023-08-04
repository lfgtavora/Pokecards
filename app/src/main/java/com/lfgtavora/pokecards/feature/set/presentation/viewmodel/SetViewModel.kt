package com.lfgtavora.pokecards.feature.set.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lfgtavora.pokecards.feature.set.data.domain.Set
import com.lfgtavora.pokecards.feature.set.data.local.SetEntity
import com.lfgtavora.pokecards.feature.set.domain.usecase.GetAllSetsByDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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

    companion object {
        const val DEFAULT_PAGE_SIZE = 16
    }

}

sealed interface FeedUiState {
    data class Success(val sets: List<Set>) : FeedUiState
    object Error : FeedUiState
    object Loading : FeedUiState
}
