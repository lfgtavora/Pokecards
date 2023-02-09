package com.lfgtavora.pokecards.feature.set.domain.usecase

import com.lfgtavora.pokecards.feature.set.data.domain.CardSet
import com.lfgtavora.pokecards.feature.set.data.response.CardSetDto
import kotlinx.coroutines.flow.Flow

interface GetAllSetsByDateUseCase {
    operator fun invoke(): Flow<List<CardSet>>
}