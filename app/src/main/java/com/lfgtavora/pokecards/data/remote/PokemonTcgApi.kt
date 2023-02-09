package com.lfgtavora.pokecards.data.remote

import com.lfgtavora.pokecards.feature.set.data.response.CardSetDto
import com.lfgtavora.pokecards.feature.set.data.response.CardSetsDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonTcgApi {

    @GET(SETS)
    suspend fun getAllSets(): CardSetsDto

    @GET("$SETS/{id}")
    suspend fun getSet(
        @Path("id") id: String
    ): Flow<CardSetDto>

    @GET(SETS)
    suspend fun filterSets(
        @Query("q") query: String
    ): Flow<CardSetsDto>

    @GET(SETS)
    suspend fun paginateSets(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): CardSetsDto

    companion object {
        private const val SETS = "sets"
    }
}
