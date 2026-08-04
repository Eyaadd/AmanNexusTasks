package com.example.recipeapp.presentation.screens.details.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.recipeapp.R

@Composable
fun DetailsImageHeader(
    imageUrl: String?,
    recipeName: String,
    durationMinutes: Int?,
    isFavorite: Boolean,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(390.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = recipeName,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    start = 12.dp,
                    top = 18.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Text(
                text = "Menu Details",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }



        Text(
            text = durationMinutes
                ?.let { "$it min" }
                ?: "--",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 18.dp),
            color = Color.White,
            fontSize = 14.sp
        )

        IconButton(
            onClick = onFavoriteClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 28.dp)
                .size(58.dp)
                .clip(CircleShape)
                .background(Color(0xFFF49A8B))
        ) {
            Icon(
                painter = painterResource(
                    if (isFavorite) {
                        R.drawable.ic_favorite_clicked
                    } else {
                        R.drawable.ic_favorite
                    }
                ),
                contentDescription = "Favorite",
                tint = Color.Unspecified,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsImageHeaderPreview() {
    DetailsImageHeader(
        imageUrl = null,
        recipeName = "Seblak Bandung",
        durationMinutes = 20,
        isFavorite = true,
        onBackClick = {},
        onFavoriteClick = {}
    )
}