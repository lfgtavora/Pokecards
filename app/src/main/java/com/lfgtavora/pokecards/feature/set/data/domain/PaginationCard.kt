package com.lfgtavora.pokecards.feature.set.data.domain

data class PaginationCard(
    val count: Int,
    val data: List<Card>,
    val page: Int,
    val pageSize: Int,
    val totalCount: Int
)