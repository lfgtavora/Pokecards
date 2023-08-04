package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName
import com.lfgtavora.pokecards.feature.set.data.domain.Tcgplayer

data class TcgplayerDto(
    @SerializedName("prices")
    val prices: PricesXDto?,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("url")
    val url: String
)

fun TcgplayerDto.asDomain() = Tcgplayer(
    prices = prices?.asDomain(),
    updatedAt = updatedAt,
    url = url
)