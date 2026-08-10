package com.example.recipeapp.presentation.screens.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun PopularCategoryCardShimmer(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(160.dp)
            .height(250.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(195.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFCE45)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        top = 60.dp,
                        bottom = 12.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .width(110.dp)
                        .height(18.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .shimmer()
                )

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(18.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .shimmer()
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .width(20.dp)
                        .height(10.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmer()
                )

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .width(90.dp)
                        .height(14.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmer()
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .width(45.dp)
                            .height(12.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .shimmer()
                    )

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .shimmer()
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(118.dp)
                .clip(CircleShape)
                .border(
                    width = 5.dp,
                    color = Color(0xFFFFF2B8),
                    shape = CircleShape
                )
                .shimmer()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PopularCategoryCardShimmerPreview() {
    RecipeAppTheme {
        PopularCategoryCardShimmer(
            modifier = Modifier.padding(16.dp)
        )
    }
}