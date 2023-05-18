package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("large")
    val large: String,
    @SerializedName("small")
    val small: String
)