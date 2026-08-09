package com.example.recipeapp.presentation.screens.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun RecipeCardShimmer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(310.dp)
            .shimmer()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(215.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .width(110.dp)
                .height(14.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(22.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )

            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun RecipeCardShimmerPreview() {
    RecipeCardShimmer(
        modifier = Modifier.padding(16.dp)
    )
}