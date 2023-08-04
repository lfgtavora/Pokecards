package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName
import com.lfgtavora.pokecards.feature.set.data.domain.ReverseHolofoil

data class ReverseHolofoilDto(
    @SerializedName("directLow")
    val directLow: Double?,
    @SerializedName("high")
    val high: Double,
    @SerializedName("low")
    val low: Double,
    @SerializedName("market")
    val market: Double,
    @SerializedName("mid")
    val mid: Double
)

fun ReverseHolofoilDto.asDomain() = ReverseHolofoil(
    directLow,
    high,
    low,
    market,
    mid
)