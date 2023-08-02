package com.lfgtavora.pokecards.feature.set.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lfgtavora.pokecards.feature.set.data.local.LegalitiesSetEntity


class LegalitiesSetTypeConverter {
    @TypeConverter
    fun fromJsonString(value: String?): LegalitiesSetEntity? {
        val listType = object : TypeToken<LegalitiesSetEntity?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toJsonString(list: LegalitiesSetEntity?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}