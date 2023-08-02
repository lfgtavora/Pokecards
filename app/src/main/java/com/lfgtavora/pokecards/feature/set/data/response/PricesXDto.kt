package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName
import com.lfgtavora.pokecards.feature.set.data.local.PricesX

data class PricesXDto(
    @SerializedName("holofoil")
    val holofoil: HolofoilDto?,
    @SerializedName("normal")
    val normal: Normal?,
    @SerializedName("reverseHolofoil")
    val reverseHolofoil: ReverseHolofoilDto?
)

fun PricesXDto.asEntity() = PricesX(
    holofoil = holofoil?.asEntity(),
    normal = normal,
    reverseHolofoil = reverseHolofoil?.asEntity()
)