package com.learning.mycomposeapp.model

data class QuoteResponseModel(
    val limit: Int,
    val quotes: List<QuoteItem>,
    val skip: Int,
    val total: Int
)