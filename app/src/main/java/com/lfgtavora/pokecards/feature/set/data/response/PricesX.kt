package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName

data class PricesX(
    @SerializedName("holofoil")
    val holofoil: Holofoil?,
    @SerializedName("normal")
    val normal: Normal?,
    @SerializedName("reverseHolofoil")
    val reverseHolofoil: ReverseHolofoil?
)