package com.lfgtavora.pokecards.feature.set.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lfgtavora.pokecards.feature.set.data.local.Ability


class AbilityListTypeConverter {
    @TypeConverter
    fun fromJsonString(value: String?): List<Ability?>? {
        val listType = object : TypeToken<List<Ability?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toJsonString(list: List<Ability?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}