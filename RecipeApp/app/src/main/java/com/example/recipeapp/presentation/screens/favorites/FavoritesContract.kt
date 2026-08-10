package com.example.recipeapp.presentation.screens.favorites

import com.example.recipeapp.domain.model.RecipeSummaryUiModel
import com.example.recipeapp.domain.model.SavedTab

object FavoritesContract {



    data class FavoritesState(
        val favoriteRecipes: List<RecipeSummaryUiModel> = emptyList(),
        val selectedTab: SavedTab = SavedTab.RECIPES,
        val isLoading: Boolean = true,
        val errorMessage: String? = null
    )

    sealed interface FavoritesIntent {

        data class OnTabSelected(
            val tab: SavedTab
        ) : FavoritesIntent

        data class OnRecipeClicked(
            val recipeId: Int
        ) : FavoritesIntent

        data class OnRemoveFavoriteClicked(
            val recipeId: Int
        ) : FavoritesIntent
    }

    sealed interface FavoritesEffect {

        data class NavigateToRecipeDetails(
            val recipeId: Int
        ) : FavoritesEffect

        data class ShowMessage(
            val message: String
        ) : FavoritesEffect
    }
}