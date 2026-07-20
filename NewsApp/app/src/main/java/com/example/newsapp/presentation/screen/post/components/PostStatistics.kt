package com.example.newsapp.presentation.screen.post.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R

@Composable
fun PostStatistics(
    likeCount: Int,
    dislikeCount: Int,
    views: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PostStatisticItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.ThumbUp,
                    contentDescription = "Likes"
                )
            },
            value = likeCount
        )

        PostStatisticItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_thumbs_down),
                    modifier = modifier.size(24.dp),
                    contentDescription = "Dislikes"
                )
            },
            value = dislikeCount
        )

        PostStatisticItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_eye),
                    modifier = modifier.size(24.dp),
                    contentDescription = "Views"
                )
            },
            value = views
        )
    }
}

@Preview
@Composable
fun PostStatisticsPreview(){
    PostStatistics(
        likeCount = 10,
        dislikeCount = 20,
        views = 500
    )
}