package com.lfgtavora.pokecards.feature.set.data.domain

data class Card(
    val abilities: List<Ability>? = null,
    val artist: String? = null,
    val attacks: List<Attack?>? = null,
    val convertedRetreatCost: Int? = null,
    val evolvesFrom: String? = null,
    val evolvesTo: List<String>? = null,
    val flavorText: String? = null,
    val hp: String? = null,
    val id: String,
    val images: Images?,
    val legalities: Legalities? = null,
    val name: String,
    val nationalPokedexNumbers: List<Int>? = null,
    val number: String? = null,
    val rarity: String? = null,
    val retreatCost: List<String>? = null,
    val rules: List<String>? = null,
    val set: Set? = null,
    val subtypes: List<String>? = null,
    val supertype: String? = null,
    val tcgplayer: Tcgplayer? = null,
    val types: List<String>? = null,
    val weaknesses: List<Weaknesse>? = null,
)

data class Attack(
    val convertedEnergyCost: Int?,
    val cost: List<String>?,
    val damage: String?,
    val name: String?,
    val text: String?
)

data class Ability(
    val name: String,
    val text: String,
    val type: String
)

data class Cardmarket(
    val prices: Prices,
    val updatedAt: String,
    val url: String
)

data class Prices(
    val averageSellPrice: Double?,
    val avg1: Double,
    val avg30: Double,
    val avg7: Double,
    val lowPrice: Double,
    val lowPriceExPlus: Double,
    val reverseHoloAvg1: Double?,
    val reverseHoloAvg30: Double?,
    val reverseHoloAvg7: Double?,
    val reverseHoloLow: Double?,
    val reverseHoloSell: Double?,
    val reverseHoloTrend: Double,
    val trendPrice: Double
)

data class Images(
    val large: String?,
    val small: String?
)

data class Legalities(
    val expanded: String,
    val unlimited: String,
)

data class Tcgplayer(
    val prices: PricesX?,
    val updatedAt: String,
    val url: String
)

data class Weaknesse(
    val type: String,
    val value: String
)

data class PricesX(
    val holofoil: Holofoil?,
    val normal: Normal?,
    val reverseHolofoil: ReverseHolofoil?
)

data class Normal(
    val directLow: Double?,
    val high: Double,
    val low: Double,
    val market: Double,
    val mid: Double
)

data class Holofoil(
    val directLow: Double?,
    val high: Double,
    val low: Double,
    val market: Double,
    val mid: Double
)

data class ReverseHolofoil(
    val directLow: Double?,
    val high: Double,
    val low: Double,
    val market: Double,
    val mid: Double
)










