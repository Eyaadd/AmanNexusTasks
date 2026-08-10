package com.example.recipeapp.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipeapp.domain.model.RecipeCategory
import com.example.recipeapp.domain.model.RecipeSummaryUiModel
import com.example.recipeapp.presentation.screens.components.RecipeSearchBar
import com.example.recipeapp.presentation.screens.home.components.ErrorContent
import com.example.recipeapp.presentation.screens.home.components.HomeHeader
import com.example.recipeapp.presentation.screens.home.components.PopularCategoryRecipes
import com.example.recipeapp.presentation.screens.home.components.PopularCategorySectionHeader
import com.example.recipeapp.presentation.screens.home.components.PopularCategorySectionHeaderShimmer
import com.example.recipeapp.presentation.screens.home.components.RecentRecipeCardShimmer
import com.example.recipeapp.presentation.screens.home.components.RecentRecipesSection
import com.example.recipeapp.presentation.screens.home.components.RecentRecipesSectionShimmer
import com.example.recipeapp.presentation.screens.components.RecipeCardShimmer
import com.example.recipeapp.presentation.screens.home.components.TrendingHeader
import com.example.recipeapp.presentation.screens.home.components.TrendingHeaderShimmer
import com.example.recipeapp.presentation.screens.home.components.TrendingRecipesRow
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    onNavigateToDetails: (Int) -> Unit,
    onNavigateToSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: HomeViewModel = koinViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is HomeContract.HomeEffect.NavigateToRecipeDetails -> {
                    onNavigateToDetails(effect.recipeId)
                }

                HomeContract.HomeEffect.NavigateToSearch -> {
                    onNavigateToSearch()
                }

                is HomeContract.HomeEffect.ShowMessage -> Unit
            }
        }
    }

    HomeScreenContent(
        state = uiState,
        onIntent = viewModel::onIntent,
        modifier = modifier
    )
}

@Composable
fun HomeScreenContent(
    state: HomeContract.HomeState,
    onIntent: (HomeContract.HomeIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 20.dp,
            vertical = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            HomeHeader()
        }

        item {
            RecipeSearchBar(
                isEditable = false,
                query = state.searchQuery,
                onQueryChanged = {
                    onIntent(
                        HomeContract.HomeIntent.OnSearchQueryChanged(it)
                    )
                },
                onClick = {
                    onIntent(
                        HomeContract.HomeIntent.OnSearchBarClicked
                    )
                }
            )
        }

        when {
            state.isLoading -> {
                homeLoadingContent()
            }

            state.errorMessage != null -> {
                item {
                    ErrorContent(
                        message = state.errorMessage,
                        onRetry = {
                            onIntent(HomeContract.HomeIntent.Retry)
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            else -> {
                homeSuccessContent(
                    state = state,
                    onIntent = onIntent
                )
            }
        }
    }
}

private fun LazyListScope.homeLoadingContent() {
    item {
        TrendingHeaderShimmer()
    }

    item {
        RecipeCardShimmer()
    }

    item {
        PopularCategorySectionHeaderShimmer()
    }

    item {
        RecentRecipeCardShimmer()
    }

    item {
        RecentRecipesSectionShimmer()
    }
}

private fun LazyListScope.homeSuccessContent(
    state: HomeContract.HomeState,
    onIntent: (HomeContract.HomeIntent) -> Unit
) {
    item {
        TrendingHeader()
    }

    item {
        TrendingRecipesRow(
            recipes = state.trendingRecipes,
            favoriteRecipeIds = state.favoriteRecipeIds,
            onRecipeClick = { id->
                onIntent(
                    HomeContract.HomeIntent.OnRecipeClicked(id)
                )
            },
            onFavoriteClick = { recipe ->
                onIntent(
                    HomeContract.HomeIntent.OnFavoriteClicked(recipe)
                )
            }
        )
    }

    item {
        PopularCategorySectionHeader(
            selectedCategory = state.selectedCategory,
            onCategorySelected = { category ->
                onIntent(
                    HomeContract.HomeIntent.OnCategorySelected(category)
                )
            }
        )
    }

    item {
        PopularCategoryRecipes(
            recipes = state.popularCategoryRecipes,
            favoriteRecipeIds = state.favoriteRecipeIds,
            onRecipeClick = { recipe ->
                onIntent(
                    HomeContract.HomeIntent.OnRecipeClicked(recipe.id)
                )
            },
            onFavoriteClick = { recipe ->
                onIntent(
                    HomeContract.HomeIntent.OnFavoriteClicked(recipe)
                )
            }
        )
    }

    item {
        RecentRecipesSection(
            recipes = state.recentRecipes,
            onRecipeClick = { recipe ->
                onIntent(
                    HomeContract.HomeIntent.OnRecipeClicked(recipe.id)
                )
            }
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    widthDp = 430,
    heightDp = 820
)
@Composable
private fun HomeScreenContentPreview() {
    HomeScreenContent(
        state = HomeContract.HomeState(
            isLoading = true,
            selectedCategory = RecipeCategory.VEGETABLES,
            trendingRecipes = listOf(
                RecipeSummaryUiModel(
                    id = 1,
                    imageUrl = null,
                    rating = 4.8,
                    readyInMinutes = 20,
                    recipeName = "Wagyu Rendang",
                    sourceName = "Jacob Jones"
                )
            ),
            popularCategoryRecipes = listOf(
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
                    recipeName = "Chicken Noodle Bowl",
                    sourceName = "Robert Fox"
                )
            ),
            favoriteRecipeIds = setOf(2)
        ),
        onIntent = {}
    )
}