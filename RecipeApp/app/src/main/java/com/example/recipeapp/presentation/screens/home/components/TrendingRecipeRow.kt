package com.example.recipeapp.presentation.screens.home.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.domain.model.RecipeSummaryUiModel

@Composable
fun TrendingRecipesRow(
    recipes: List<RecipeSummaryUiModel>,
    favoriteRecipeIds: Set<Int>,
    onRecipeClick: (RecipeSummaryUiModel) -> Unit,
    onFavoriteClick: (RecipeSummaryUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = recipes,
            key = { recipe -> recipe.id }
        ) { recipe ->
            TrendingRecipeCard(
                recipe = recipe,
                isFavorite = recipe.id in favoriteRecipeIds,
                modifier = Modifier.width(300.dp),
                onClick = {
                    onRecipeClick(recipe)
                },
                onFavoriteClick = {
                    onFavoriteClick(recipe)
                }
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 430)
@Composable
private fun TrendingRecipesRowPreview() {
    TrendingRecipesRow(
        recipes = listOf(
            RecipeSummaryUiModel(
                id = 1,
                imageUrl = null,
                rating = 4.8,
                readyInMinutes = 80,
                recipeName = "Rendang Wagyu Pare",
                sourceName = "Wade Warren"
            ),
            RecipeSummaryUiModel(
                id = 2,
                imageUrl = null,
                rating = 4.6,
                readyInMinutes = 45,
                recipeName = "Chicken and Vegetable Bowl",
                sourceName = "Foodista"
            )
        ),
        favoriteRecipeIds = setOf(2),
        onRecipeClick = {},
        onFavoriteClick = {}
    )
}