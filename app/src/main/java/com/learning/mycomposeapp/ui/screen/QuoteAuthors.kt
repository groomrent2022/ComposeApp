package com.learning.mycomposeapp.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.learning.mycomposeapp.MainActivity.Companion.TAG
import com.learning.mycomposeapp.R
import com.learning.mycomposeapp.viewmodel.QuoteViewModel

@Composable
fun QuoteAuthors(onBackClicked: (authorName: String) -> Unit) {
    val quoteViewModel: QuoteViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        quoteViewModel.getQuoteCategory()
    }

    val quoteCategoryList = quoteViewModel.quoteCategory.collectAsState()
    Log.d(TAG, "Response: ${quoteCategoryList.value}")
    if (quoteCategoryList.value.isEmpty()) {
        Loader()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 2 columns
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(quoteCategoryList.value) { item ->
                AuthorItemView(item) {
                    onBackClicked(item)
                }
            }
        }
    }
}

@Composable
fun AuthorItemView(item: String, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // make each box square
            .padding(8.dp)
            .clickable { onClick(item) },
        contentAlignment = Alignment.Center
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize() // fill the Box
        )
        // Title
        Text(
            text = item,
            fontSize = 26.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = Color.Black,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
    }
}
