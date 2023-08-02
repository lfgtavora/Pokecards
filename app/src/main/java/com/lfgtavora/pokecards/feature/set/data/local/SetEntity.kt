package com.lfgtavora.pokecards.feature.set.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sets")
data class SetEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val images: ImagesSetEntity,
    val legalities: LegalitiesSetEntity,
    val name: String,
    val printedTotal: Int,
    val ptcgoCode: String?,
    val releaseDate: String,
    val series: String,
    val total: Int,
    val updatedAt: String
)
class LegalitiesSetEntity(
    val expanded: String?,
    val standard: String?,
    val unlimited: String
)
class ImagesSetEntity(
    val logo: String,
    val symbol: String
)