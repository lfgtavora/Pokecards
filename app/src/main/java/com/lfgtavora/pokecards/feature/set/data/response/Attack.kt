package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName

data class Attack(
    @SerializedName("convertedEnergyCost")
    val convertedEnergyCost: Int,
    @SerializedName("cost")
    val cost: List<String>,
    @SerializedName("damage")
    val damage: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("text")
    val text: String
)