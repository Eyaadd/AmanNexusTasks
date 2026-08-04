package com.example.recipeapp.presentation.screens.home

import com.example.recipeapp.data.model.RecipeCategory
import com.example.recipeapp.data.model.RecipeSummaryUiModel

object HomeContract {
    data class HomeState(
        val isLoading: Boolean = false,
        val isCategoryLoading: Boolean = false,
        val searchQuery: String = "",
        val trendingRecipes: List<RecipeSummaryUiModel> = emptyList(),
        val selectedCategory: RecipeCategory = RecipeCategory.VEGETABLES,
        val popularCategoryRecipes: List<RecipeSummaryUiModel> = emptyList(),
        val recentRecipes: List<RecipeSummaryUiModel> = emptyList(),
        val favoriteRecipeIds: Set<Int> = emptySet(),
        val errorMessage: String? = null
    )

    sealed interface HomeIntent {

        data object OnSearchBarClicked : HomeIntent

        data class OnCategorySelected(
            val category: RecipeCategory
        ) : HomeIntent

        data class OnSearchQueryChanged(
            val query: String
        ) : HomeIntent

        data class OnRecipeClicked(
            val recipe: RecipeSummaryUiModel
        ) : HomeIntent

        data class OnFavoriteClicked(
            val recipe: RecipeSummaryUiModel
        ) : HomeIntent

        data object Retry : HomeIntent
    }

    sealed interface HomeEffect {


        data object NavigateToSearch : HomeEffect


        data class NavigateToRecipeDetails(
            val recipeId: Int
        ) : HomeEffect

        data class ShowMessage(
            val message: String
        ) : HomeEffect
    }
}