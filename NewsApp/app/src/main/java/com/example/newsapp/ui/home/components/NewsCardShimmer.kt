package com.example.newsapp.ui.home.components


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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NewsCardShimmer(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.width(290.dp),
        shape = RoundedCornerShape(10.dp),
        color = Color(0xFFF9FCFE)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(161.dp)
            ) {
                ShimmerBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(161.dp)
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                )

                ShimmerBox(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                        .width(70.dp)
                        .height(28.dp)
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(6.dp)
                        )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            ShimmerBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(6.dp)
                    )
            )

            Spacer(modifier = Modifier.height(6.dp))

            ShimmerBox(
                modifier = Modifier
                    .width(220.dp)
                    .height(18.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(6.dp)
                    )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ShimmerBox(
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                color = Color.LightGray,
                                shape = CircleShape
                            )
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    ShimmerBox(
                        modifier = Modifier
                            .width(90.dp)
                            .height(14.dp)
                            .background(
                                color = Color.LightGray,
                                shape = RoundedCornerShape(6.dp)
                            )
                    )
                }

                ShimmerBox(
                    modifier = Modifier
                        .width(45.dp)
                        .height(14.dp)
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(6.dp)
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsCardShimmerPreview() {
    NewsCardShimmer()
}