package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName

data class CardDto(
    @SerializedName("abilities")
    val abilities: List<Ability>?,
    @SerializedName("artist")
    val artist: String,
    @SerializedName("attacks")
    val attacks: List<Attack>,
    @SerializedName("cardmarket")
    val cardmarket: Cardmarket,
    @SerializedName("convertedRetreatCost")
    val convertedRetreatCost: Int?,
    @SerializedName("evolvesFrom")
    val evolvesFrom: String?,
    @SerializedName("evolvesTo")
    val evolvesTo: List<String>?,
    @SerializedName("flavorText")
    val flavorText: String?,
    @SerializedName("hp")
    val hp: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: Images,
    @SerializedName("legalities")
    val legalities: Legalities,
    @SerializedName("name")
    val name: String,
    @SerializedName("nationalPokedexNumbers")
    val nationalPokedexNumbers: List<Int>,
    @SerializedName("number")
    val number: String,
    @SerializedName("rarity")
    val rarity: String,
    @SerializedName("retreatCost")
    val retreatCost: List<String>?,
    @SerializedName("rules")
    val rules: List<String>?,
    @SerializedName("set")
    val set: CardSetDto,
    @SerializedName("subtypes")
    val subtypes: List<String>,
    @SerializedName("supertype")
    val supertype: String,
    @SerializedName("tcgplayer")
    val tcgplayer: Tcgplayer,
    @SerializedName("types")
    val types: List<String>,
    @SerializedName("weaknesses")
    val weaknesses: List<Weaknesse>
)