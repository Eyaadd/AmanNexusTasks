package com.example.newsapp.presentation.screen.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.presentation.theme.roboto
import com.example.newsapp.presentation.theme.sourceSans
import com.example.newsapp.presentation.theme.whiteColor


@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    category: String,
    title: String,
    @DrawableRes authorIcon: Int,
    author: String,
    publishDate: String
) {

    Card(
        modifier = modifier.width(290.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = whiteColor
        )
    ) {

        Column(
            modifier = Modifier.padding(8.dp)
        ) {

            Box(
                modifier = Modifier
                    .width(285.dp)
                    .height(161.dp)
            ) {
                Image(
                    modifier = Modifier
                        .matchParentSize()
                        .clip(RoundedCornerShape(10.dp)),
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Text(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                        .background(
                            color = Color(0xFF2ABAFF),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(
                            horizontal = 8.dp,
                            vertical = 6.dp
                        ),
                    text = category,
                    color = Color.White,
                    fontFamily = sourceSans,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.padding(top = 12.dp))

            Text(
                text = title, fontFamily = roboto, fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.padding(top = 12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(authorIcon), contentDescription = null
                    )

                    Spacer(modifier = Modifier.padding(start = 6.dp))

                    Text(
                        text = author, fontFamily = sourceSans
                    )

                }

                Text(
                    text = publishDate, fontFamily = sourceSans, color = Color.Gray
                )

            }

        }

    }

}

@Preview()
@Composable
private fun NewsCardPreview() {
   NewsCard(
        image = R.drawable.news_image,
        category = "Sports",
        title = "Manchester City signs another world class midfielder for the upcoming season.",
        authorIcon = R.drawable.ic_person,
        author = "John Doe",
        publishDate = "2h ago"
    )

}