package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName
import com.lfgtavora.pokecards.feature.set.data.local.ImagesSetEntity

data class ImagesSetDto(
    @SerializedName("logo")
    val logo: String,
    @SerializedName("symbol")
    val symbol: String
)

fun ImagesSetDto.asEntity() = ImagesSetEntity(logo, symbol)