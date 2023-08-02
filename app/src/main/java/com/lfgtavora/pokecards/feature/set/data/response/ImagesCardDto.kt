package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName
import com.lfgtavora.pokecards.feature.set.data.local.Images

data class ImagesCardDto(
    @SerializedName("large")
    val large: String,
    @SerializedName("small")
    val small: String
)

fun ImagesCardDto.asEntity() = Images(large, small)