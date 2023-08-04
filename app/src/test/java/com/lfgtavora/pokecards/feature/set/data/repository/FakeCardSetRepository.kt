package com.lfgtavora.pokecards.feature.set.data.repository

import com.lfgtavora.pokecards.feature.set.data.domain.ImagesSet
import com.lfgtavora.pokecards.feature.set.data.domain.LegalitiesSet
import com.lfgtavora.pokecards.feature.set.data.domain.PaginationCard
import com.lfgtavora.pokecards.feature.set.data.domain.Set
import com.lfgtavora.pokecards.feature.set.data.response.SetDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCardSetRepository : CardSetRepository {

    private val sets: List<Set> = mutableListOf<Set>().apply {
        for (i in 1..16) {
            val set = Set(
                "set$i",
                ImagesSet("logo$i", "symbol$i"),
                LegalitiesSet("expanded$i", "standard$i", "unlimited$i"),
                "Set $i",
                100 + i * 10,
                "ptcgo$i",
                "2023-01-0$i",
                "Series $i",
                200 + i * 10,
                "2023-08-03"
            )
            add(set)
        }
    }

    override suspend fun getAllSets(): Flow<List<SetDto>> {
        TODO("Not yet implemented")
    }

    override suspend fun filterSets(query: String): Flow<List<SetDto>> {
        TODO("Not yet implemented")
    }

    override fun paginateSets(page: Int, pageSize: Int): Flow<List<Set>> =
        flowOf(sets)

    override suspend fun paginateCards(
        setId: String,
        page: Int,
        pageSize: Int
    ): Result<PaginationCard> {
        TODO("Not yet implemented")
    }
}