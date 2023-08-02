package com.lfgtavora.pokecards.feature.setdetail.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lfgtavora.pokecards.feature.set.data.datasource.PokemonTcgRemoteDataSource
import com.lfgtavora.pokecards.feature.set.data.response.CardDto
import retrofit2.HttpException
import java.io.IOException

class SetDetailsDataSource(
    private val api: PokemonTcgRemoteDataSource
) : PagingSource<Params, CardDto>() {
    override fun getRefreshKey(state: PagingState<Params, CardDto>): Params? {
        return null
    }

    override suspend fun load(params: LoadParams<Params>): LoadResult<Params, CardDto> = try {

        val cards = api
            .paginateCards(params.key!!.setId, params.key?.page ?: STARTING_PAGE_INDEX, PAGE_SIZE)
            .data

        LoadResult.Page(
            data = cards,
            prevKey = params.key,
            nextKey = params.key?.copy(page = params.key!!.page + 1)
        )
    } catch (e: IOException) {
        LoadResult.Error(e)
    } catch (e: HttpException) {
        LoadResult.Error(e)
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
        private const val PAGE_SIZE = 32
    }

}


data class Params(
    val setId: String,
    val pageSize: Int = 1,
    val page: Int = 1
)