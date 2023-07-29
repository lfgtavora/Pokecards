package com.lfgtavora.pokecards.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.lfgtavora.pokecards.feature.set.data.local.CardEntity

@Dao
interface CardDao {

    @Upsert
    suspend fun upsertAll(cards: List<CardEntity>)

    @Query("SELECT * FROM cardentity")
    fun pagingSource(): PagingSource<Int, CardEntity>

    @Query("DELETE FROM cardentity")
    suspend fun clearAll()
}