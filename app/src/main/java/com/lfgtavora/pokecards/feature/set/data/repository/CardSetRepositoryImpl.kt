package com.lfgtavora.pokecards.feature.set.data.repository

import com.lfgtavora.pokecards.feature.set.data.datasource.CardSetsRemoteDataSource
import com.lfgtavora.pokecards.feature.set.data.domain.CardSet
import com.lfgtavora.pokecards.feature.set.data.response.CardSetsDto
import com.lfgtavora.pokecards.feature.set.data.response.asDomain
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ActivityScoped
data class CardSetRepositoryImpl @Inject constructor(
    private val cardSetsRemoteDataSource: CardSetsRemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : CardSetRepository {

    override suspend fun getAllSets() =
        TODO()

    override suspend fun filterSets(query: String) =
        TODO()

    override fun paginateSets(page: Int, pageSize: Int): Flow<List<CardSet>> = flow {
        emit(
            cardSetsRemoteDataSource.paginateSets(page, pageSize)
        )
    }.map(CardSetsDto::asDomain).flowOn(dispatcher)

}
