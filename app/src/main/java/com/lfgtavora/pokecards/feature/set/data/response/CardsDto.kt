package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName

data class CardsDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val cards: List<CardDto>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pageSize")
    val pageSize: Int,
    @SerializedName("totalCount")
    val totalCount: Int
)