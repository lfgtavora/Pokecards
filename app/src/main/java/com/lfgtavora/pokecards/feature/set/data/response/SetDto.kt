package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName
import com.lfgtavora.pokecards.feature.set.data.local.SetEntity
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

fun SetDto.asEntity() = SetEntity(
    id = id,
    name = name,
    releaseDate = releaseDate,
    total = total,
    images = imagesSet.asEntity(),
    legalities = legalities.asEntity(),
    printedTotal = printedTotal,
    updatedAt = updatedAt,
    series = series,
    ptcgoCode = ptcgoCode
)
