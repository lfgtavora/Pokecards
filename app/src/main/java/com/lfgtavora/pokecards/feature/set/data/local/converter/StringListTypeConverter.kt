package com.lfgtavora.pokecards.feature.set.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lfgtavora.pokecards.feature.set.data.local.Attack


class StringListTypeConverter {
    @TypeConverter
    fun fromJsonString(value: String?): List<String?>? {
        val listType = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toJsonString(list: List<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}