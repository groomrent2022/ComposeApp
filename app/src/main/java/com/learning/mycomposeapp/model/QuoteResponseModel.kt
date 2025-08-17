package com.learning.mycomposeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote_list")
data class QuoteResponseModel(
    @PrimaryKey
    val limit: Int,
    val quotes: List<QuoteItem>,
    val skip: Int,
    val total: Int
)