package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName
import com.lfgtavora.pokecards.feature.set.data.domain.Legalities

data class LegalitiesDto(
    @SerializedName("expanded")
    val expanded: String,
    @SerializedName("unlimited")
    val unlimited: String,
)

fun LegalitiesDto.asDomain() = Legalities(expanded, unlimited)
