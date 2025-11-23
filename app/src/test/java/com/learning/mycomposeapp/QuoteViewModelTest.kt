package com.learning.mycomposeapp
import androidx.lifecycle.SavedStateHandle
import com.learning.mycomposeapp.model.QuoteItem
import com.learning.mycomposeapp.repository.QuoteRepository
import com.learning.mycomposeapp.viewmodel.QuoteViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class QuoteViewModelTest {

    private lateinit var repository: QuoteRepository
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var viewModel: QuoteViewModel

    // Fake StateFlows from repository
    private val fakeCategoryFlow = MutableStateFlow(listOf<String>())
    private val fakeQuoteListFlow = MutableStateFlow(listOf<QuoteItem>())

    @Before
    fun setup() {
        repository = mock()

        // Stub repository StateFlows
        whenever(repository.quoteCategoryRes).thenReturn(fakeCategoryFlow)
        whenever(repository.quoteListByCategoryRes).thenReturn(fakeQuoteListFlow)

        savedStateHandle = SavedStateHandle()

        viewModel = QuoteViewModel(
            savedStateHandle = savedStateHandle,
            repository = repository
        )
    }

    @Test
    fun `test getQuoteCategory triggers repository method`() = runTest(UnconfinedTestDispatcher()) {
        // WHEN
        viewModel.getQuoteCategory()

        // THEN
        verify(repository, times(1)).getQuoteCategory()
    }

    @Test
    fun `test getQuoteList triggers repository when author_name present`() = runTest(UnconfinedTestDispatcher()) {
        // GIVEN
        savedStateHandle["author_name"] = "Einstein"

        // Recreate ViewModel so savedStateHandle is applied
        viewModel = QuoteViewModel(savedStateHandle, repository)

        // WHEN
        viewModel.getQuoteList()

        // THEN
        verify(repository, times(1)).getQuoteListByCategory("Einstein")
    }

    @Test
    fun `quoteCategory StateFlow should return repository flow`() {
        // GIVEN
        fakeCategoryFlow.value = listOf("Love", "Life")

        // THEN
        assertEquals(listOf("Love", "Life"), viewModel.quoteCategory.value)
    }

    @Test
    fun `quoteListByCategory StateFlow should return repository flow`() {
        // GIVEN
        val list = listOf(QuoteItem(id = 1, quote = "Be Kind", author = "Buddha"))
        fakeQuoteListFlow.value = list

        // THEN
        assertEquals(list, viewModel.quoteListByCategory.value)
    }
}
