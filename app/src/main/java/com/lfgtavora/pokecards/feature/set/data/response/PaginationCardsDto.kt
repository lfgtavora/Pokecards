package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName
import com.lfgtavora.pokecards.feature.set.data.domain.PaginationCard

data class PaginationCardsDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val data: List<CardDto>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pageSize")
    val pageSize: Int,
    @SerializedName("totalCount")
    val totalCount: Int
)

fun PaginationCardsDto.asDomain() =
    PaginationCard(
        count = count,
        data = data.map { it.asDomain() },
        page = page,
        pageSize = pageSize,
        totalCount = totalCount
    )