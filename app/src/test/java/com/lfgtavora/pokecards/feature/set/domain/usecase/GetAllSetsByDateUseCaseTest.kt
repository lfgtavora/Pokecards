package com.lfgtavora.pokecards.feature.set.domain.usecase

import com.lfgtavora.pokecards.feature.set.data.repository.CardSetRepository
import com.lfgtavora.pokecards.feature.set.data.repository.FakeCardSetRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class GetAllSetsByDateUseCaseTest {

    private lateinit var getAllSetsByDateUseCase: GetAllSetsByDateUseCase
    private lateinit var cardSetRepository: CardSetRepository

    @Before
    fun setUp() {
        cardSetRepository = FakeCardSetRepository()
        getAllSetsByDateUseCase = GetAllSetsByDateUseCase(cardSetRepository)
    }

    @Test
    fun `Get Movies List, correct movie list return`(): Unit = runBlocking {
        val sets = getAllSetsByDateUseCase().first()
        val setsSortedByDescending = cardSetRepository
            .paginateSets()
            .first()
            .sortedByDescending { it.releaseDate }

        sets shouldBeEqualTo setsSortedByDescending

    }


}