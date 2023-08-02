package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName
import com.lfgtavora.pokecards.feature.set.data.local.Tcgplayer

data class TcgplayerDto(
    @SerializedName("prices")
    val prices: PricesXDto,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("url")
    val url: String
)

fun TcgplayerDto.asEntity() = Tcgplayer(
    prices = prices.asEntity(),
    updatedAt = updatedAt,
    url = url
)