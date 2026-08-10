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
fun RecentRecipesSectionShimmer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .width(150.dp)
                .height(28.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
                .shimmer()
        )

        LazyRow(
            modifier = Modifier.padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(4) {
                RecentRecipeCardShimmer()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecentRecipesSectionShimmerPreview() {
    RecipeAppTheme {
        RecentRecipesSectionShimmer(
            modifier = Modifier.padding(16.dp)
        )
    }
}