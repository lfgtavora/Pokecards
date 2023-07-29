package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName
import com.lfgtavora.pokecards.feature.set.data.domain.Set
import java.text.SimpleDateFormat
import java.util.*

data class CardSetDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val imagesDto: ImagesDto,
    @SerializedName("legalities")
    val legalitiesDto: LegalitiesDto,
    @SerializedName("name")
    val name: String,
    @SerializedName("printedTotal")
    val printedTotal: Int,
    @SerializedName("ptcgoCode")
    val ptcgoCode: String?,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("series")
    val series: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("updatedAt")
    val updatedAt: String
)

fun CardSetDto.asDomain() =
    Set(
        id = id,
        name = name,
        releaseDate = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).parse(releaseDate),
        total = total,
        logo = imagesDto.logo,
        symbol = imagesDto.symbol
    )
