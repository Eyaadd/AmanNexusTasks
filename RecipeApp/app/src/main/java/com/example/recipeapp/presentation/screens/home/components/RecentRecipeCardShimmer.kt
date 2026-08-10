package com.example.recipeapp.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.presentation.theme.RecipeAppTheme
import com.valentinilk.shimmer.shimmer

@Composable
fun RecentRecipeCardShimmer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.width(130.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color.LightGray)
                .shimmer()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.LightGray)
                .shimmer()
        )

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .width(90.dp)
                .height(16.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.LightGray)
                .shimmer()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .shimmer()
            )

            Spacer(modifier = Modifier.width(6.dp))

            Box(
                modifier = Modifier
                    .width(70.dp)
                    .height(12.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray)
                    .shimmer()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecentRecipeCardShimmerPreview() {
    RecipeAppTheme {
        RecentRecipeCardShimmer(
            modifier = Modifier.padding(16.dp)
        )
    }
}