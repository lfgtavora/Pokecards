package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName

data class LegalitiesDto(
    @SerializedName("expanded")
    val expanded: String?,
    @SerializedName("standard")
    val standard: String?,
    @SerializedName("unlimited")
    val unlimited: String
)