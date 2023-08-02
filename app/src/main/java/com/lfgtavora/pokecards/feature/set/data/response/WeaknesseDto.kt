package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName
import com.lfgtavora.pokecards.feature.set.data.local.Weaknesse

data class WeaknesseDto(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: String
)

fun WeaknesseDto.asEntity() = Weaknesse(type, value)