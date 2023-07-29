package com.lfgtavora.pokecards.feature.set.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.lfgtavora.pokecards.data.local.AppDatabase
import com.lfgtavora.pokecards.feature.set.data.domain.Card
import com.lfgtavora.pokecards.feature.set.data.local.CardEntity
import com.lfgtavora.pokecards.feature.set.data.repository.CardSetRepository
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CardRemoteMediator(
    private val setId: String,
    private val cardRepository: CardSetRepository,
    private val database: AppDatabase,
) : RemoteMediator<Int, CardEntity>() {

    var prevKey = 1
    var nextKey = 2

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CardEntity>
    ): MediatorResult {

        return try {

            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> nextKey
            }

            val cardPagination = cardRepository.paginateCards(
                setId = setId,
                page = loadKey,
                pageSize = state.config.pageSize
            )

            database.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    database.cardDao.clearAll()
                }
                val cardEntities = cardPagination.data.map { it. }
                database.cardDao.upsertAll(cardEntities)
            }

            prevKey = nextKey
            nextKey = cardPagination.page + 1

            MediatorResult.Success(
                endOfPaginationReached = cardPagination.count <= cardPagination.totalCount
            )

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}