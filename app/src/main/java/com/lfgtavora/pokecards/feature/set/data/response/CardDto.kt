package com.lfgtavora.pokecards.feature.set.data.response


import com.google.gson.annotations.SerializedName
import com.lfgtavora.pokecards.feature.set.data.domain.Card

data class CardDto(
    @SerializedName("abilities")
    val abilities: List<AbilityDto>?,
    @SerializedName("artist")
    val artist: String,
    @SerializedName("attacks")
    val attacks: List<AttackDto>?,
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
    val images: ImagesCardDto,
    @SerializedName("legalities")
    val legalitiesDto: LegalitiesDto,
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
    val set: SetDto,
    @SerializedName("subtypes")
    val subtypes: List<String>,
    @SerializedName("supertype")
    val supertype: String,
    @SerializedName("tcgplayer")
    val tcgplayer: TcgplayerDto,
    @SerializedName("types")
    val types: List<String>,
    @SerializedName("weaknesses")
    val weaknesses: List<WeaknesseDto>?
)

fun CardDto.asDomain() = Card(
    id = id,
    abilities = abilities?.map { it.asDomain() },
    artist = artist,
    attacks = attacks?.map { it.asDomain() },
    convertedRetreatCost = convertedRetreatCost,
    evolvesFrom = evolvesFrom,
    evolvesTo = evolvesTo,
    flavorText = flavorText,
    hp = hp,
    images = images?.asDomain(),
    legalities = legalitiesDto?.asDomain(),
    name = name,
    nationalPokedexNumbers = nationalPokedexNumbers,
    number = number,
    rarity = rarity,
    retreatCost = retreatCost,
    rules = rules,
    set = set?.asDomain(),
    subtypes = subtypes,
    supertype = supertype,
    tcgplayer = tcgplayer?.asDomain(),
    types = types,
    weaknesses = weaknesses?.map { it.asDomain() },
)