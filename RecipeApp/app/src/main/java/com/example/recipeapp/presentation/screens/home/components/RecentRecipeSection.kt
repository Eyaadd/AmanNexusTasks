package com.example.recipeapp.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import com.example.recipeapp.R
import com.example.recipeapp.domain.model.RecipeSummaryUiModel

@Composable
fun RecentRecipesSection(
    recipes: List<RecipeSummaryUiModel>,
    onRecipeClick: (RecipeSummaryUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.recent_recipes),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        LazyRow(
            modifier = Modifier.padding(top = 12.dp),
            horizontalArrangement =
                Arrangement.spacedBy(14.dp)
        ) {
            items(
                items = recipes,
                key = { it.id }
            ) { recipe ->
                RecentRecipeCard(
                    recipe = recipe,
                    onClick = {
                        onRecipeClick(recipe)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 420)
@Composable
fun RecentRecipesSectionPreview() {
    RecentRecipesSection(
        recipes = listOf(
            RecipeSummaryUiModel(
                id = 3,
                imageUrl = null,
                rating = 4.6,
                readyInMinutes = 35,
                recipeName = "Ayam Goreng Sambal Ijo",
                sourceName = "Guy Hawkins"
            ),
            RecipeSummaryUiModel(
                id = 4,
                imageUrl = null,
                rating = 4.4,
                readyInMinutes = 30,
                recipeName = "Ayam Goreng Sambal Ijo",
                sourceName = "Robert Fox"
            ),
            RecipeSummaryUiModel(
                id = 5,
                imageUrl = null,
                rating = 4.5,
                readyInMinutes = 25,
                recipeName = "Noodle Bowl",
                sourceName = "Cody Fisher"
            )
        ),
        onRecipeClick = {},
        modifier = Modifier.padding(16.dp)
    )
}
