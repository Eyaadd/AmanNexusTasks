package com.example.newsapp.presentation.screen.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
fun NewsFeedCardShimmer(
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier.width(380.dp),
        shape = RoundedCornerShape(10.dp),
        color = Color(0xFFF9FCFE)
    ) {

        Column(
            modifier = Modifier.padding(8.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                ShimmerBox(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            Color.LightGray,
                            CircleShape
                        )
                )

                Spacer(Modifier.width(6.dp))

                Column {

                    ShimmerBox(
                        modifier = Modifier
                            .width(90.dp)
                            .height(14.dp)
                            .background(
                                Color.LightGray,
                                RoundedCornerShape(6.dp)
                            )
                    )

                    Spacer(Modifier.height(6.dp))

                    ShimmerBox(
                        modifier = Modifier
                            .width(60.dp)
                            .height(12.dp)
                            .background(
                                Color.LightGray,
                                RoundedCornerShape(6.dp)
                            )
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            ShimmerBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
                    .background(
                        Color.LightGray,
                        RoundedCornerShape(6.dp)
                    )
            )

            Spacer(Modifier.height(8.dp))

            ShimmerBox(
                modifier = Modifier
                    .width(220.dp)
                    .height(18.dp)
                    .background(
                        Color.LightGray,
                        RoundedCornerShape(6.dp)
                    )
            )

            Spacer(Modifier.height(12.dp))

            ShimmerBox(
                modifier = Modifier
                    .width(80.dp)
                    .height(30.dp)
                    .border(
                        1.dp,
                        Color.LightGray,
                        RoundedCornerShape(6.dp)
                    )
                    .background(
                        Color.LightGray,
                        RoundedCornerShape(6.dp)
                    )
            )

            Spacer(Modifier.height(16.dp))

            ShimmerBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(191.dp)
                    .background(
                        Color.LightGray,
                        RoundedCornerShape(10.dp)
                    )
            )

            Spacer(Modifier.height(12.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsFeedCardShimmerPreview() {
    NewsFeedCardShimmer()
}