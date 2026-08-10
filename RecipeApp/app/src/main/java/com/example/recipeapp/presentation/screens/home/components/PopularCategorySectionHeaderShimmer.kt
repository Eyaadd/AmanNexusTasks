package com.example.recipeapp.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.presentation.theme.RecipeAppTheme
import com.valentinilk.shimmer.shimmer

@Composable
fun PopularCategorySectionHeaderShimmer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .width(180.dp)
                .height(28.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
                .shimmer()
        )

        LazyRow(
            modifier = Modifier.padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            items(5) { index ->
                val width = when (index) {
                    0 -> 48.dp
                    1 -> 72.dp
                    2 -> 64.dp
                    3 -> 82.dp
                    else -> 58.dp
                }

                Box(
                    modifier = Modifier
                        .width(width)
                        .height(18.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color.LightGray)
                        .shimmer()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PopularCategorySectionHeaderShimmerPreview() {
    RecipeAppTheme {
        PopularCategorySectionHeaderShimmer(
            modifier = Modifier.padding(16.dp)
        )
    }
}