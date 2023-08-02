package com.lfgtavora.pokecards.feature.set.data.domain

import java.util.*

data class SetEntity(
    val id: String,
    val name: String,
    val releaseDate: Date?,
    val total: Int,
    val logo: String,
    val symbol: String
)
