package com.lfgtavora.pokecards.feature.set.data.repository

import com.lfgtavora.pokecards.feature.set.data.domain.Set
import com.lfgtavora.pokecards.feature.set.data.response.CardSetDto
import com.lfgtavora.pokecards.feature.set.data.response.PaginationCardsDto
import kotlinx.coroutines.flow.Flow

interface CardSetRepository {
    suspend fun getAllSets(): Flow<List<CardSetDto>>
    suspend fun filterSets(query: String): Flow<List<CardSetDto>>
    fun paginateSets(page: Int = 1, pageSize: Int = 10): Flow<List<Set>>
    suspend fun paginateCards(
        setId: String,
        page: Int = 1,
        pageSize: Int = 10
    ): PaginationCardsDto
}
