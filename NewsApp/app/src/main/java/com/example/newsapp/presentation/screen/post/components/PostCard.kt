package com.example.newsapp.presentation.screen.post.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.data.source.remote.models.Post

@Composable
fun PostCard(
    post: Post,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )

            PostTags(
                tags = post.tags
            )

            PostStatistics(
                likeCount = post.likeCount,
                dislikeCount = post.dislikeCount,
                views = post.views
            )
        }
    }
}

@Preview
@Composable
fun PostCardPreview() {
    PostCard(
        post = Post(
            id = 1,
            title = "World Cup",
            body = "Cristiano Ronaldo delivers another historic performance as Portugal secures a dramatic victory. Fans around the world celebrate what many are calling one of the greatest moments in football history.",
            tags = listOf(
                "Football",
                "World Cup",
                "Portugal",
                "Cristiano Ronaldo"
            ),
            likeCount = 12_458,
            dislikeCount = 317,
            views = 256_431
        )
    )
}