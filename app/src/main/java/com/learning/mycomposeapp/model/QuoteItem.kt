package com.learning.mycomposeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote_detail")
data class QuoteItem(
    @PrimaryKey
    val id: Int,
    val author: String,
    val quote: String
)