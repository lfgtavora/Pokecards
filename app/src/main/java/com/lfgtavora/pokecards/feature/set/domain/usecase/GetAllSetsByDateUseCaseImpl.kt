package com.lfgtavora.pokecards.feature.set.domain.usecase

import com.lfgtavora.pokecards.feature.set.data.domain.Set
import com.lfgtavora.pokecards.feature.set.data.repository.CardSetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllSetsByDateUseCaseImpl @Inject constructor(
    private val repository: CardSetRepository,
) : GetAllSetsByDateUseCase {

    override operator fun invoke(): Flow<List<Set>> =
        repository.paginateSets().map { sets ->
            sets.sortedByDescending { it.releaseDate }
        }
}