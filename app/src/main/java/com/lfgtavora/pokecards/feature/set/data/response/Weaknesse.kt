package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName

data class Weaknesse(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: String
)