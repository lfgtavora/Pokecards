package com.lfgtavora.pokecards.feature.set.domain.usecase

import com.lfgtavora.pokecards.feature.set.data.domain.Set
import kotlinx.coroutines.flow.Flow

interface GetAllSetsByDateUseCase {
    operator fun invoke(): Flow<List<Set>>
}