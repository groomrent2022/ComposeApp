package com.learning.mycomposeapp.di

import android.content.Context
import androidx.room.Room
import com.learning.mycomposeapp.network.QuoteApi
import com.learning.mycomposeapp.room.QuoteDao
import com.learning.mycomposeapp.room.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class QuoteModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideQuoteApi(retrofit: Retrofit): QuoteApi {
        return retrofit.create(QuoteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context): QuoteDatabase {
     return Room.databaseBuilder(app, QuoteDatabase::class.java, "quotes_db")
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun provideQuoteDao(db: QuoteDatabase): QuoteDao = db.quoteDao()
}