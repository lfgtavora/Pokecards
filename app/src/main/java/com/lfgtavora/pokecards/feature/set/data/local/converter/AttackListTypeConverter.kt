package com.lfgtavora.pokecards.feature.set.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lfgtavora.pokecards.feature.set.data.local.Ability
import com.lfgtavora.pokecards.feature.set.data.local.Attack


class AttackListTypeConverter {
    @TypeConverter
    fun fromJsonString(value: String?): List<Attack?>? {
        val listType = object : TypeToken<List<Attack?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toJsonString(list: List<Attack?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}