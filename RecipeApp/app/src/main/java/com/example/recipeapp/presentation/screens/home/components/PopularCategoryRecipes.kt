package com.example.recipeapp.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.data.model.RecipeSummaryUiModel

@Composable
fun PopularCategoryRecipes(
    recipes: List<RecipeSummaryUiModel>,
    favoriteRecipeIds: Set<Int>,
    onRecipeClick: (RecipeSummaryUiModel) -> Unit,
    onFavoriteClick: (RecipeSummaryUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(
            items = recipes, key = { it.id }) { recipe ->
            PopularCategoryCard(
                recipe = recipe,
                isFavorite = recipe.id in favoriteRecipeIds,
                onClick = {
                    onRecipeClick(recipe)
                },
                onFavoriteClick = {
                    onFavoriteClick(recipe)
                })
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 420)
@Composable
fun PopularCategoryRecipesPreview() {
    PopularCategoryRecipes(
        recipes = listOf(
        RecipeSummaryUiModel(
            id = 1,
            imageUrl = null,
            rating = 4.8,
            readyInMinutes = 20,
            recipeName = "Wagyu Rendang",
            sourceName = "Jacob Jones"
        ), RecipeSummaryUiModel(
            id = 2,
            imageUrl = null,
            rating = 4.7,
            readyInMinutes = 20,
            recipeName = "Seblak Bandung",
            sourceName = "Ralph Edwards"
        )
    ),
        favoriteRecipeIds = setOf(2),
        onRecipeClick = {},
        onFavoriteClick = {},
        modifier = Modifier.padding(vertical = 16.dp)
    )
}