package com.example.recipeapp.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipeapp.data.model.RecipeCategory
import com.example.recipeapp.data.model.RecipeSummaryUiModel
import com.example.recipeapp.presentation.screens.home.components.ErrorContent
import com.example.recipeapp.presentation.screens.home.components.HomeHeader
import com.example.recipeapp.presentation.screens.home.components.PopularCategoryRecipes
import com.example.recipeapp.presentation.screens.home.components.PopularCategorySectionHeader
import com.example.recipeapp.presentation.screens.home.components.RecentRecipesSection
import com.example.recipeapp.presentation.screens.home.components.RecipeCardShimmer
import com.example.recipeapp.presentation.screens.components.RecipeSearchBar
import com.example.recipeapp.presentation.screens.home.components.TrendingHeader
import com.example.recipeapp.presentation.screens.home.components.TrendingRecipesRow
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    onNavigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
    onNavigateToSearch: () -> Unit,
) {
    val viewModel : HomeViewModel = koinViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {

                is HomeContract.HomeEffect.NavigateToRecipeDetails -> {
                    onNavigateToDetails(effect.recipeId)
                }


                is HomeContract.HomeEffect.ShowMessage -> {
                }

                HomeContract.HomeEffect.NavigateToSearch -> onNavigateToSearch()
            }
        }
    }

    HomeScreenContent(state = uiState, onIntent = viewModel::onIntent, modifier = modifier)
}

@Composable
fun HomeScreenContent(
    state: HomeContract.HomeState,
    onIntent: (HomeContract.HomeIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        contentPadding = PaddingValues(
            horizontal = 20.dp,
            vertical = 16.dp
        ),
        verticalArrangement =
            Arrangement.spacedBy(24.dp)
    ) {
        item {
            HomeHeader()
        }

        item {
            RecipeSearchBar(
                query = state.searchQuery,
                onQueryChanged = {
                    onIntent(
                        HomeContract.HomeIntent
                            .OnSearchQueryChanged(it)
                    )
                },
                onClick = {
                    onIntent(
                        HomeContract.HomeIntent.OnSearchBarClicked
                    )
                }
            )
        }
        item {
            TrendingHeader()
        }

        item {
            when {
                state.isLoading -> {
                    RecipeCardShimmer()
                }

                state.errorMessage != null -> {
                    ErrorContent(
                        message = state.errorMessage,
                        onRetry = {
                            onIntent(HomeContract.HomeIntent.Retry)
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                state.trendingRecipes.isEmpty() -> {
                    Text(
                        text = "No trending recipes found"
                    )
                }

                else -> {
                    TrendingRecipesRow(
                        recipes = state.trendingRecipes,
                        favoriteRecipeIds = state.favoriteRecipeIds,
                        onRecipeClick = { recipe ->
                            onIntent(
                                HomeContract.HomeIntent.OnRecipeClicked(recipe)
                            )
                        },
                        onFavoriteClick = { recipe ->
                            onIntent(
                                HomeContract.HomeIntent.OnFavoriteClicked(recipe)
                            )
                            Log.d("FAVORITE_TEST", "${state.favoriteRecipeIds}")
                        }
                    )
                }
            }
        }

        item {
            PopularCategorySectionHeader(
                selectedCategory =
                    state.selectedCategory,
                onCategorySelected = {
                    onIntent(
                        HomeContract.HomeIntent
                            .OnCategorySelected(it)
                    )
                }
            )
        }

        item {
            if (state.isLoading || state.isCategoryLoading) {
                RecipeCardShimmer()
            } else {
                PopularCategoryRecipes(
                    recipes =
                        state.popularCategoryRecipes,
                    favoriteRecipeIds =
                        state.favoriteRecipeIds,
                    onRecipeClick = {
                        onIntent(
                            HomeContract.HomeIntent
                                .OnRecipeClicked(it)
                        )
                    },
                    onFavoriteClick = {
                        onIntent(
                            HomeContract.HomeIntent
                                .OnFavoriteClicked(it)
                        )
                    }
                )
            }
        }

        item {
            if (!state.isLoading) {
                RecentRecipesSection(
                    recipes = state.recentRecipes,
                    onRecipeClick = {
                        onIntent(
                            HomeContract.HomeIntent
                                .OnRecipeClicked(it)
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 430, heightDp = 820)
@Composable
private fun HomeScreenContentPreview() {
    HomeScreenContent(
        state = HomeContract.HomeState(
            selectedCategory = RecipeCategory.VEGETABLES,
            popularCategoryRecipes = listOf(
                RecipeSummaryUiModel(
                    id = 1,
                    imageUrl = null,
                    rating = 4.8,
                    readyInMinutes = 20,
                    recipeName = "Wagyu Rendang",
                    sourceName = "Jacob Jones"
                ),
                RecipeSummaryUiModel(
                    id = 2,
                    imageUrl = null,
                    rating = 4.7,
                    readyInMinutes = 20,
                    recipeName = "Seblak Bandung",
                    sourceName = "Ralph Edwards"
                )
            ),
            recentRecipes = listOf(
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
            favoriteRecipeIds = setOf(2)
        ),
        onIntent = {}
    )
}