package com.learning.mycomposeapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.learning.mycomposeapp.model.QuoteResponseModel

@Database(entities = [QuoteResponseModel::class], version = 1, exportSchema = false)
@TypeConverters(QuoteListConverter::class)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
}