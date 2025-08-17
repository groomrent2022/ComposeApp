package com.learning.mycomposeapp.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.learning.mycomposeapp.model.QuoteItem

class QuoteListConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromQuoteItemList(value: List<QuoteItem>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toQuoteItemList(value: String): List<QuoteItem> {
        val listType = object : TypeToken<List<QuoteItem>>() {}.type
        return gson.fromJson(value, listType)
    }
}
