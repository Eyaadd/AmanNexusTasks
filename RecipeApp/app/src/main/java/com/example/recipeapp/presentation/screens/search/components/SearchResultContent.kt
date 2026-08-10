package com.example.recipeapp.presentation.screens.search.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.recipeapp.R
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
                message = stringResource(R.string.search_favorite_recipes),
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
                message = stringResource(R.string.no_recipes_found),
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
