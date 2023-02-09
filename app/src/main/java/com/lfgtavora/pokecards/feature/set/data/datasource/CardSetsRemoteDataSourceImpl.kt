package com.lfgtavora.pokecards.feature.set.data.datasource

import com.lfgtavora.pokecards.data.remote.PokemonTcgApi
import com.lfgtavora.pokecards.feature.set.data.response.CardSetsDto
import javax.inject.Inject

class CardSetsRemoteDataSourceImpl @Inject constructor(
    private val api: PokemonTcgApi
) : CardSetsRemoteDataSource {

    override suspend fun paginateSets(page: Int, pageSize: Int): CardSetsDto =
        api.getAllSets()
}
