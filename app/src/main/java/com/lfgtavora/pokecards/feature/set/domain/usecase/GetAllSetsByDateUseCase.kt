package com.lfgtavora.pokecards.feature.set.domain.usecase

import com.lfgtavora.pokecards.feature.set.data.local.SetEntity
import kotlinx.coroutines.flow.Flow

interface GetAllSetsByDateUseCase {
    operator fun invoke(): Flow<List<SetEntity>>
}