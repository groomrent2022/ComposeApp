package com.learning.mycomposeapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.learning.mycomposeapp.model.QuoteResponseModel

@Dao
interface QuoteDao {
    @Insert
    suspend fun saveAllQuotes(item: QuoteResponseModel)

    @Query("SELECT * from quote_list")
    suspend fun getAllQuotes(): QuoteResponseModel?

    @Query("DELETE FROM quote_list")
    suspend fun deleteAllQuotes()

    /*@Query("SELECT * from quote_list where total = :total")
    fun getById(total: Int): QuoteItem?

    @Update
    suspend fun update(item: QuoteItem)

    @Delete
    suspend fun delete(item: QuoteItem)*/
}