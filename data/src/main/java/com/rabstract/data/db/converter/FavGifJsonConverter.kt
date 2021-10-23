package com.rabstract.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.rabstract.model.GifData

class FavGifJsonConverter {

    @TypeConverter
    fun objToJsonString(data: GifData?): String? {

        return Gson().toJson(data).toString()

    }

    @TypeConverter
    fun jsonStringToObj(data: String?): GifData? {

        return Gson().fromJson(data, GifData::class.java)

    }
}