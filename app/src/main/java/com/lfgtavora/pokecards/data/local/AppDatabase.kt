package com.lfgtavora.pokecards.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lfgtavora.pokecards.feature.set.data.local.CardEntity
import com.lfgtavora.pokecards.feature.set.data.local.ImagesSetEntity
import com.lfgtavora.pokecards.feature.set.data.local.LegalitiesSetEntity
import com.lfgtavora.pokecards.feature.set.data.local.SetEntity
import com.lfgtavora.pokecards.feature.set.data.local.converter.AbilityListTypeConverter
import com.lfgtavora.pokecards.feature.set.data.local.converter.AttackListTypeConverter
import com.lfgtavora.pokecards.feature.set.data.local.converter.CardImageTypeConverter
import com.lfgtavora.pokecards.feature.set.data.local.converter.ImageSetTypeConverter
import com.lfgtavora.pokecards.feature.set.data.local.converter.LegalitiesSetTypeConverter
import com.lfgtavora.pokecards.feature.set.data.local.converter.StringListTypeConverter


@Database(
    entities = [
        CardEntity::class,
        SetEntity::class,
    ],
    version = 1
)
@TypeConverters(
    AbilityListTypeConverter::class,
    AttackListTypeConverter::class,
    StringListTypeConverter::class,
    CardImageTypeConverter::class,
    LegalitiesSetTypeConverter::class,
    ImageSetTypeConverter::class,
)

abstract class AppDatabase : RoomDatabase() {
    abstract val cardDao: CardDao
}