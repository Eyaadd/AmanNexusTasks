package com.example.recipeapp.presentation.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipeapp.domain.model.RecipeSummaryUiModel
import com.example.recipeapp.presentation.screens.components.RecipeSearchBar
import com.example.recipeapp.presentation.screens.search.components.SearchResultContent
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    onNavigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is SearchContract.SearchEffect.NavigateToRecipeDetails -> {
                    onNavigateToDetails(effect.recipeId)
                }

                is SearchContract.SearchEffect.ShowMessage -> {}
            }
        }
    }

    SearchScreenContent(
        state = state,
        onIntent = viewModel::onIntent,
        modifier = modifier
    )
}

@Composable
fun SearchScreenContent(
    state: SearchContract.SearchState,
    onIntent: (SearchContract.SearchIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        RecipeSearchBar(
            query = state.searchQuery,
            onQueryChanged = { query ->
                onIntent(
                    SearchContract.SearchIntent.OnSearchQueryChanged(query)
                )
            },
            onClick = {},
            modifier = Modifier.padding(
                top = 12.dp,
                bottom = 16.dp
            )
        )

        SearchResultContent(
            state = state,
            onRecipeClicked = { recipeId ->
                onIntent(
                    SearchContract.SearchIntent.OnRecipeClicked(recipeId)
                )
            },
            onFavoriteClicked = { recipe ->
                onIntent(
                    SearchContract.SearchIntent.OnFavoriteClicked(recipe)
                )
            },
            modifier = Modifier.weight(1f)
        )
    }
}


@Composable
fun SearchMessage(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun SearchScreenPreview() {
    SearchScreenContent(
        state = SearchContract.SearchState(
            searchQuery = "Chicken",
            isLoading = false,
            searchResult = listOf(
                RecipeSummaryUiModel(
                    id = 1,
                    imageUrl = null,
                    rating = 4.8,
                    readyInMinutes = 35,
                    recipeName = "Grilled Chicken Pasta",
                    sourceName = "Recipe Kitchen"
                ),
                RecipeSummaryUiModel(
                    id = 2,
                    imageUrl = null,
                    rating = 4.5,
                    readyInMinutes = 25,
                    recipeName = "Chicken Caesar Salad",
                    sourceName = "Healthy Food"
                ),
                RecipeSummaryUiModel(
                    id = 3,
                    imageUrl = null,
                    rating = 4.2,
                    readyInMinutes = 45,
                    recipeName = "Creamy Chicken Soup",
                    sourceName = "Home Recipes"
                ),
                RecipeSummaryUiModel(
                    id = 4,
                    imageUrl = null,
                    rating = 4.7,
                    readyInMinutes = 30,
                    recipeName = "Spicy Chicken Tacos",
                    sourceName = "Tasty Meals"
                )
            ),
            errorMessage = null
        ),
        onIntent = {}
    )
}