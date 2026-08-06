package com.example.recipeapp.presentation.screens.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipeapp.domain.model.RecipeSummaryUiModel
import com.example.recipeapp.presentation.screens.components.TrendingRecipeCard

@Composable
fun SearchRecipeGrid(
    favoriteRecipeIds: Set<Int>,
    recipes: List<RecipeSummaryUiModel>,
    onRecipeClicked: (Int) -> Unit,
    onFavoriteClicked: (RecipeSummaryUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            bottom = 16.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = recipes,
            key = { recipe -> recipe.id }
        ) { recipe ->
            TrendingRecipeCard(
                recipe = recipe,
                isFavorite = recipe.id in favoriteRecipeIds,
                onClick = {
                    onRecipeClicked(recipe.id)
                },
                onFavoriteClick = {
                    onFavoriteClicked(recipe)
                }
            )
        }
    }
}