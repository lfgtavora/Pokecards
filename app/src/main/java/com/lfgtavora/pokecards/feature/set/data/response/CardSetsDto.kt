package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName

data class CardSetsDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val cardSets: List<CardSetDto>,
    @SerializedName("page")
    val page: Int = 1,
    @SerializedName("pageSize")
    val pageSize: Int = 250,
    @SerializedName("totalCount")
    val totalCount: Int
)

fun CardSetsDto.asDomain() =
    cardSets.map(CardSetDto::asDomain)
