package com.lfgtavora.pokecards.feature.set.data.domain

data class Card(
    val id: String,
    val image: Image,
    val name: String,
    val set: Set
)

class Image(
    val large: String,
    val small: String
)
