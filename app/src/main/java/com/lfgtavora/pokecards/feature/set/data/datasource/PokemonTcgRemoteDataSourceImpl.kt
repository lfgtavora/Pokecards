package com.lfgtavora.pokecards.feature.set.data.datasource

import com.lfgtavora.pokecards.data.remote.PokemonTcgApi
import com.lfgtavora.pokecards.feature.set.data.response.CardSetsDto
import com.lfgtavora.pokecards.feature.set.data.response.PaginationCardsDto
import javax.inject.Inject

class PokemonTcgRemoteDataSourceImpl @Inject constructor(
    private val api: PokemonTcgApi
) : PokemonTcgRemoteDataSource {

    override suspend fun paginateSets(page: Int, pageSize: Int): CardSetsDto =
        api.getAllSets()

    override suspend fun paginateCards(setId: String, page: Int, pageSize: Int): PaginationCardsDto =
        api.paginateCards("set.id:$setId", page, pageSize)

}
