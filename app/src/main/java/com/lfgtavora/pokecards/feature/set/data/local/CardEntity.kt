package com.lfgtavora.pokecards.feature.set.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lfgtavora.pokecards.feature.set.data.response.Normal

@Entity(tableName = "cards")
data class CardEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
//    val supertype: String,
    val name: String,
//    val abilities: MutableList<Ability>?,
//    val artist: String,
//    val attacks: MutableList<Attack>?,
//    val convertedRetreatCost: Int?,
//    val evolvesFrom: String?,
//    val evolvesTo: MutableList<String>?,
//    val flavorText: String?,
//    val hp: String,
    val images: Images,
//    val legalities: Legalities,
//    val nationalPokedexNumbers: List<Int>,
//    val number: String,
//    val rarity: String,
//    val retreatCost: List<String>?,
//    val rules: List<String>?,
//    val set: SetEntity,
//    val subtypes: List<String>,
//    val tcgplayer: Tcgplayer,
//    val types: List<String>,
//    val weaknesses: List<Weaknesse>
)

class Images(
    val large: String,
    val small: String
)

class Ability(
    val name: String,
    val text: String,
    val type: String
)

data class Weaknesse(
    val type: String,
    val value: String
)

class Attack(
    val convertedEnergyCost: Int,
    val cost: List<String>,
    val damage: String,
    val name: String,
    val text: String
)

class Legalities(
    val expanded: String,
    val unlimited: String
)

class Tcgplayer(
    val prices: PricesX,
    val updatedAt: String,
    val url: String
)

class PricesX(
    val holofoil: Holofoil?,
    val normal: Normal?,
    val reverseHolofoil: ReverseHolofoil?
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