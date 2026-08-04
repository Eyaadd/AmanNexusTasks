package com.example.recipeapp.presentation.screens.favorites

import com.example.recipeapp.presentation.screens.favorites.components.SavedRecipesTabs
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.recipeapp.data.model.RecipeSummaryUiModel
import com.example.recipeapp.presentation.screens.favorites.components.EmptySavedContent
import com.example.recipeapp.presentation.screens.home.components.ErrorContent
import com.example.recipeapp.presentation.screens.home.components.TrendingRecipeCard
import com.example.recipeapp.presentation.theme.RecipeAppTheme
import com.example.recipeapp.presentation.theme.RecipeYellow
import com.example.recipeapp.presentation.theme.ScreenTextColor
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesScreen(
    onNavigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is FavoritesContract.FavoritesEffect.NavigateToRecipeDetails -> {
                    onNavigateToDetails(effect.recipeId)
                }

                is FavoritesContract.FavoritesEffect.ShowMessage -> {
                }
            }
        }
    }

    FavoritesScreenContent(
        state = state,
        onIntent = viewModel::onIntent,
        modifier = modifier
    )
}

@Composable
fun FavoritesScreenContent(
    state: FavoritesContract.FavoritesState,
    onIntent: (FavoritesContract.FavoritesIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Saved recipes",
            modifier = Modifier.padding(top = 20.dp),
            color = ScreenTextColor,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        SavedRecipesTabs(
            selectedTab = state.selectedTab,
            onTabSelected = { selectedTab ->
                onIntent(
                    FavoritesContract.FavoritesIntent.OnTabSelected(
                        selectedTab
                    )
                )
            },
            modifier = Modifier.padding(top = 20.dp)
        )

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = RecipeYellow
                    )
                }
            }

            state.errorMessage != null -> {
                ErrorContent(
                    message = state.errorMessage,
                    onRetry = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                )
            }

            state.selectedTab == FavoritesContract.SavedTab.VIDEO -> {
                EmptySavedContent(
                    message = "No saved videos yet",
                    modifier = Modifier.fillMaxSize()
                )
            }

            state.favoriteRecipes.isEmpty() -> {
                EmptySavedContent(
                    message = "No saved recipes yet",
                    modifier = Modifier.fillMaxSize()
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp),
                    contentPadding = PaddingValues(bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(
                        items = state.favoriteRecipes,
                        key = { recipe -> recipe.id }
                    ) { recipe ->
                        TrendingRecipeCard(
                            recipe = recipe,
                            isFavorite = true,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                onIntent(
                                    FavoritesContract.FavoritesIntent
                                        .OnRecipeClicked(recipe.id)
                                )
                            },
                            onFavoriteClick = {
                                onIntent(
                                    FavoritesContract.FavoritesIntent
                                        .OnRemoveFavoriteClicked(recipe.id)
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 430, heightDp = 900)
@Composable
private fun FavoritesScreenContentPreview() {
    RecipeAppTheme {
        FavoritesScreenContent(
            state = FavoritesContract.FavoritesState(
                isLoading = false,
                selectedTab = FavoritesContract.SavedTab.RECIPES,
                favoriteRecipes = listOf(
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
                )
            ),
            onIntent = {}
        )
    }
}