package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName
import com.lfgtavora.pokecards.feature.set.data.domain.Ability

data class AbilityDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("type")
    val type: String
)

fun AbilityDto.asDomain() = Ability(name, text, type)