package com.example.recipeapp.presentation.screens.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.recipeapp.R
import com.example.recipeapp.data.model.RecipeSummaryUiModel

@Composable
fun PopularCategoryCard(
    recipe: RecipeSummaryUiModel,
    isFavorite: Boolean,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit,
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
            onClick = onClick,
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFCE45)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 12.dp, end = 12.dp, top = 60.dp, bottom = 12.dp
                    ), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = recipe.recipeName,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "by",
                    modifier = Modifier.padding(top = 4.dp),
                    color = Color(0xFFB98618),
                    fontSize = 12.sp
                )

                Text(
                    text = recipe.sourceName ?: "Unknown source",
                    color = Color(0xFFB98618),
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = recipe.readyInMinutes?.let { "$it Mins" } ?: "--",
                        color = Color(0xFF9F4C26),
                        fontSize = 12.sp)

                    Icon(
                        painter = painterResource(
                            if (isFavorite) {
                                R.drawable.ic_favorite_clicked
                            } else {
                                R.drawable.ic_favorite
                            }
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(onClick = onFavoriteClick),
                        tint = Color.Unspecified
                    )
                }
            }
        }

        AsyncImage(
            model = recipe.imageUrl,
            contentDescription = recipe.recipeName,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(118.dp)
                .clip(CircleShape)
                .border(
                    width = 5.dp, color = Color(0xFFFFF2B8), shape = CircleShape
                ),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PopularCategoryCardPreview() {
    PopularCategoryCard(
        recipe = RecipeSummaryUiModel(
        id = 1,
        imageUrl = null,
        rating = 4.8,
        readyInMinutes = 20,
        recipeName = "Wagyu Rendang",
        sourceName = "Jacob Jones"
    ),
        isFavorite = false,
        onClick = {},
        onFavoriteClick = {},
        modifier = Modifier.padding(16.dp)
    )
}