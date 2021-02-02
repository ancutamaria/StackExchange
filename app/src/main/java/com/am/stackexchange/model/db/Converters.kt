package com.am.stackexchange.model.db

import androidx.room.TypeConverter
import com.am.stackexchange.model.data.Item
import com.am.stackexchange.model.data.Owner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {
    @TypeConverter
    fun fromStringToList(field: String): List<String> {
        return field.split(",")
    }
    @TypeConverter
    fun fromListToString(field: List<String>): String {
        return field.joinToString(",")
    }

    @TypeConverter
    fun fromItemsToList(list: List<Item?>?): String? {
        if (list == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Item?>?>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun fromStringToItems(string: String?): List<Item>? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Item?>?>() {}.type
        return gson.fromJson<List<Item>>(string, type)
    }

    @TypeConverter
    fun fromOwnerToString(owner: Owner): String {
        val gson = Gson()
        return gson.toJson(owner)
    }

    @TypeConverter
    fun fromStringToOwner(string: String): Owner {
        val gson = Gson()
        val owner = object : TypeToken<Owner>() {
        }.type
        return gson.fromJson(string, owner)
    }

}