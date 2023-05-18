package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName

data class ImagesX(
    @SerializedName("logo")
    val logo: String,
    @SerializedName("symbol")
    val symbol: String
)