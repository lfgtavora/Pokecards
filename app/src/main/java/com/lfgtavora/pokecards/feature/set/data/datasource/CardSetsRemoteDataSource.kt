package com.lfgtavora.pokecards.feature.set.data.datasource

import com.lfgtavora.pokecards.feature.set.data.response.CardSetDto
import com.lfgtavora.pokecards.feature.set.data.response.CardSetsDto

interface CardSetsRemoteDataSource {
    suspend fun paginateSets(page: Int = 1, pageSize: Int = 10): CardSetsDto
}