package com.example.recipeapp.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.recipeapp.presentation.theme.ScreenTextColor
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TrendingHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "Trending Now🔥",
            color = ScreenTextColor,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun TrendingHeaderPreview() {
    TrendingHeader(
        modifier = Modifier.padding(16.dp)
    )
}