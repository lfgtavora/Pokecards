package com.lfgtavora.pokecards.feature.set.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lfgtavora.pokecards.feature.set.data.local.Attack
import com.lfgtavora.pokecards.feature.set.data.local.Images


class CardImageTypeConverter {
    @TypeConverter
    fun fromJsonString(value: String?): Images? {
        val listType = object : TypeToken<Images?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toJsonString(list: Images?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}