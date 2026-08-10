package com.example.newsapp.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreenShimmer(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Spacer(Modifier.height(24.dp))

            ShimmerBox(
                modifier = Modifier
                    .width(220.dp)
                    .height(28.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(6.dp)
                    )
            )

            Spacer(Modifier.height(6.dp))

            ShimmerBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(6.dp)
                    )
            )
        }

        item {
            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ShimmerBox(
                    modifier = Modifier
                        .width(120.dp)
                        .height(20.dp)
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(6.dp)
                        )
                )

                ShimmerBox(
                    modifier = Modifier
                        .width(50.dp)
                        .height(18.dp)
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(6.dp)
                        )
                )
            }

            Spacer(Modifier.height(16.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(4) {
                    NewsCardShimmer()
                }
            }
        }

        item {
            Spacer(Modifier.height(32.dp))

            ShimmerBox(
                modifier = Modifier
                    .width(150.dp)
                    .height(20.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(6.dp)
                    )
            )

            Spacer(Modifier.height(16.dp))
        }

        items(4) {
            NewsFeedCardShimmer()

            Spacer(Modifier.height(16.dp))
        }
    }
}