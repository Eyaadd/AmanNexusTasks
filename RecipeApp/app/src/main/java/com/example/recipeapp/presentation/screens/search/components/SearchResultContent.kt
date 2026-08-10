package com.example.recipeapp.presentation.screens.search.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recipeapp.domain.model.RecipeSummaryUiModel
import com.example.recipeapp.presentation.screens.search.SearchContract
import com.example.recipeapp.presentation.screens.search.SearchMessage

@Composable
fun SearchResultContent(
    state: SearchContract.SearchState,
    onRecipeClicked: (Int) -> Unit,
    onFavoriteClicked: (RecipeSummaryUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        state.searchQuery.isBlank() -> {
            SearchMessage(
                message = "Search for your favorite recipes",
                modifier = modifier
            )
        }

        state.isLoading -> {
            SearchShimmerGrid()
        }

        state.errorMessage != null -> {
            SearchMessage(
                message = state.errorMessage,
                modifier = modifier
            )
        }

        state.searchResult.isEmpty() -> {
            SearchMessage(
                message = "No recipes found",
                modifier = modifier
            )
        }

        else -> {
            SearchRecipeGrid(
                recipes = state.searchResult,
                onRecipeClicked = onRecipeClicked,
                onFavoriteClicked = onFavoriteClicked,
                modifier = modifier,
                favoriteRecipeIds = state.favoriteRecipeIds
            )
        }
    }
}