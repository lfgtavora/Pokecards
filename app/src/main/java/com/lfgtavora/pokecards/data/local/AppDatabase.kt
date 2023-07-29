package com.lfgtavora.pokecards.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lfgtavora.pokecards.feature.set.data.local.CardEntity

@Database(
    entities = [CardEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract val cardDao: CardDao
}