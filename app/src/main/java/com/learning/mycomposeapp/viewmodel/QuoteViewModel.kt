package com.learning.mycomposeapp.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavArgs
import androidx.navigation.navArgument
import com.learning.mycomposeapp.model.QuoteItem
import com.learning.mycomposeapp.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: QuoteRepository
) : ViewModel() {
     val quoteCategory: StateFlow<List<String>>
        get() = repository.quoteCategoryRes
     val quoteListByCategory: StateFlow<List<QuoteItem>>
        get() = repository.quoteListByCategoryRes

    fun getQuoteCategory() {
        viewModelScope.launch {
            repository.getQuoteCategory()
        }
    }


    fun getQuoteList() {
        val authName: String? = savedStateHandle["author_name"]
        viewModelScope.launch {
            authName?.let {  repository.getQuoteListByCategory(authName)}
        }
    }
}