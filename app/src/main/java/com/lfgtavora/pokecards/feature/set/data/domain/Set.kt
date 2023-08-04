package com.lfgtavora.pokecards.feature.set.data.domain

data class Set(
    val id: String,
    val images: ImagesSet,
    val legalities: LegalitiesSet,
    val name: String,
    val printedTotal: Int?,
    val ptcgoCode: String?,
    val releaseDate: String?,
    val series: String?,
    val total: Int,
    val updatedAt: String?
)

data class LegalitiesSet(
    val expanded: String?,
    val standard: String?,
    val unlimited: String
)
data class ImagesSet(
    val logo: String,
    val symbol: String
)