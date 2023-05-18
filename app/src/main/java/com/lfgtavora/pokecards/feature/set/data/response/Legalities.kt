package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName

data class Legalities(
    @SerializedName("expanded")
    val expanded: String,
    @SerializedName("unlimited")
    val unlimited: String
)