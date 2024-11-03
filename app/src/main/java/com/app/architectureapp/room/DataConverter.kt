package com.app.architectureapp.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object DataConverter {
    private val typeDocument: Type = object : TypeToken<List<String?>>() {}.type

    @TypeConverter
    fun fromValueItemList(documentListString: List<String?>?): String? {
        if (documentListString == null) {
            return null
        }
        return Gson().toJson(documentListString, typeDocument)
    }

    @TypeConverter
    fun toValueItemList(documentListString: String?): List<String?>? {
        if (documentListString == null) {
            return null
        }
        return Gson().fromJson(documentListString, typeDocument)
    }
}