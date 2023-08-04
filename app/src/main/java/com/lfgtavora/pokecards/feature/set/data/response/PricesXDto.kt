package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName
import com.lfgtavora.pokecards.feature.set.data.domain.PricesX

data class PricesXDto(
    @SerializedName("holofoil")
    val holofoil: HolofoilDto?,
    @SerializedName("normal")
    val normal: NormalDto?,
    @SerializedName("reverseHolofoil")
    val reverseHolofoil: ReverseHolofoilDto?
)

fun PricesXDto.asDomain() = PricesX(
    holofoil = holofoil?.asDomain(),
    normal = normal?.asDomain(),
    reverseHolofoil = reverseHolofoil?.asDomain()
)