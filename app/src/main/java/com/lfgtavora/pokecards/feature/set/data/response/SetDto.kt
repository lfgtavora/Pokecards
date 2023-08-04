package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName
import com.lfgtavora.pokecards.feature.set.data.domain.Set
import java.util.*

data class SetDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val imagesSet: ImagesSetDto,
    @SerializedName("legalities")
    val legalities: LegalitiesSetDto,
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

fun SetDto.asDomain() = Set(
    id = id,
    name = name,
    total = total,
    images = imagesSet.asDomain(),
    legalities = legalities.asDomain(),
    printedTotal = printedTotal,
    updatedAt = updatedAt,
    series = series,
    ptcgoCode = ptcgoCode,
    releaseDate = releaseDate
)
