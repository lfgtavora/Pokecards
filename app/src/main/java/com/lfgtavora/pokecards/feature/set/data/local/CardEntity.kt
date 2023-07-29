package com.lfgtavora.pokecards.feature.set.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val image: ImageEntity,
    val supertype: String,
    val name: String,
)

class ImageEntity(
    val large: String,
    val small: String
)
