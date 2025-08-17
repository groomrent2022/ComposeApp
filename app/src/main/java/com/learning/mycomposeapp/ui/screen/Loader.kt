package com.learning.mycomposeapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learning.mycomposeapp.R

@Composable
fun Loader() {
    Column(
    modifier = Modifier
    .fillMaxSize()
    .padding(24.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Loader image (animated)
        Image(
            painter = painterResource(id = R.drawable.loader), // Replace with your loader image
            contentDescription = "Loading...",
            modifier = Modifier
                .size(56.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Loading text
        Text(
            text = "Loading...",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
    }
}