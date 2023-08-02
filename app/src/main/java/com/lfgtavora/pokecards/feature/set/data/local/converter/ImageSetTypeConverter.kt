package com.lfgtavora.pokecards.feature.set.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lfgtavora.pokecards.feature.set.data.local.Attack
import com.lfgtavora.pokecards.feature.set.data.local.ImagesSetEntity


class ImageSetTypeConverter {
    @TypeConverter
    fun fromJsonString(value: String?): ImagesSetEntity? {
        val listType = object : TypeToken<ImagesSetEntity?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toJsonString(list: ImagesSetEntity?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}