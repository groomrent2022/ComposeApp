package com.learning.mycomposeapp.network

import com.learning.mycomposeapp.model.QuoteResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApi {
    @GET("quotes")
    suspend fun getQuoteCategories(): Response<QuoteResponseModel>

    @GET("quotes")
    suspend fun getQuoteListByCategory(): Response<QuoteResponseModel>
}