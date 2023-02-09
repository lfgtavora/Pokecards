package com.lfgtavora.pokecards.feature.set.data.repository

import com.lfgtavora.pokecards.feature.set.data.domain.CardSet
import com.lfgtavora.pokecards.feature.set.data.response.CardSetDto
import kotlinx.coroutines.flow.Flow

interface CardSetRepository {
    suspend fun getAllSets(): Flow<List<CardSetDto>>
    suspend fun filterSets(query: String): Flow<List<CardSetDto>>
    fun paginateSets(page: Int = 1, pageSize: Int = 10): Flow<List<CardSet>>
}
