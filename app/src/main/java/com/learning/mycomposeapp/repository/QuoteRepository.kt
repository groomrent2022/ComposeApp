package com.learning.mycomposeapp.repository

import android.util.Log
import com.learning.mycomposeapp.model.QuoteItem
import com.learning.mycomposeapp.network.QuoteApi
import com.learning.mycomposeapp.room.QuoteDao
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QuoteRepository @Inject constructor(
    private val quoteDao: QuoteDao,
    private val quoteApi: QuoteApi
) {

    private val quoteCategory = MutableStateFlow<List<String>>(emptyList())
    val quoteCategoryRes: StateFlow<List<String>> get() = quoteCategory

    suspend fun getQuoteCategory() {

        val quoteListFromRoomDb = getQuoteList(quoteDao.getAllQuotes()?.quotes)
        if (quoteListFromRoomDb.isEmpty()) {
            Log.d("QuoteRepository", "No data found in Room DB - $quoteListFromRoomDb")
            val response = quoteApi.getQuoteCategories()
            if (response.isSuccessful && response.body() != null) {
                val quoteList = getQuoteList(response.body()?.quotes)
                if (quoteList.isEmpty())
                    Log.d("QuoteRepository", quoteList.toString())
                else {
                    Log.d("QuoteRepository", "Data loaded from Rest API - $quoteList")
                    emitData(quoteList)
                    // Saving data in room db
                    quoteDao.saveAllQuotes(response.body()!!)
                }
            }
        } else {
            Log.d("QuoteRepository", "Data loaded from Room DB - $quoteListFromRoomDb")
            emitData(quoteListFromRoomDb)
        }
    }

    private suspend fun emitData(quoteList: List<String>) {
        quoteCategory.emit(quoteList)
    }

    private fun getQuoteList(quotes: List<QuoteItem>?): List<String> {
        val quoteList = arrayListOf<String>()
        if (quotes?.isNotEmpty() == true) {
            quotes.forEach {
                quoteList.add(it.author)
            }
            return quoteList.distinct()
        } else return emptyList()
    }


    private val quoteListByCategory = MutableStateFlow<List<QuoteItem>>(emptyList())
    val quoteListByCategoryRes: StateFlow<List<QuoteItem>> get() = quoteListByCategory

    suspend fun getQuoteListByCategory(category: String) {
        val response = quoteApi.getQuoteListByCategory()
        if (response.isSuccessful && response.body() != null) {
            val quoteList = response.body()?.quotes?.filter {
                it.author == category
            }
            if (quoteList?.isNotEmpty() == true)
                quoteListByCategory.emit(quoteList)
            else
                Log.d("Quote API Response: ", quoteList.toString())
        }
    }
}