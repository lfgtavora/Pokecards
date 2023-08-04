package com.lfgtavora.pokecards.feature.set.data.repository

import com.lfgtavora.pokecards.feature.set.data.domain.PaginationCard
import com.lfgtavora.pokecards.feature.set.data.domain.Set
import com.lfgtavora.pokecards.feature.set.data.local.SetEntity
import com.lfgtavora.pokecards.feature.set.data.response.SetDto
import com.lfgtavora.pokecards.feature.set.data.response.PaginationCardsDto
import kotlinx.coroutines.flow.Flow

interface CardSetRepository {
    suspend fun getAllSets(): Flow<List<SetDto>>
    suspend fun filterSets(query: String): Flow<List<SetDto>>
    fun paginateSets(page: Int = 1, pageSize: Int = 10): Flow<List<Set>>
    suspend fun paginateCards(
        setId: String,
        page: Int = 1,
        pageSize: Int = 10
    ): Result<PaginationCard>
}
