package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName
import com.lfgtavora.pokecards.feature.set.data.local.LegalitiesSetEntity

data class LegalitiesSetDto(
    @SerializedName("expanded")
    val expanded: String,
    @SerializedName("unlimited")
    val unlimited: String,
    @SerializedName("standard")
    val standard: String?,
)

fun LegalitiesSetDto.asEntity() = LegalitiesSetEntity(
    expanded,
    standard,
    unlimited
)