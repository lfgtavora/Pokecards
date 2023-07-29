package com.lfgtavora.pokecards.feature.set.data.datasource

import com.lfgtavora.pokecards.feature.set.data.response.CardSetsDto
import com.lfgtavora.pokecards.feature.set.data.response.PaginationCardsDto

interface PokemonTcgRemoteDataSource {
    suspend fun paginateSets(page: Int = 1, pageSize: Int = 10): CardSetsDto
    suspend fun paginateCards(setId: String, page: Int = 1, pageSize: Int = 10): PaginationCardsDto
}
