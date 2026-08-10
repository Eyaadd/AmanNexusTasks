package com.example.recipeapp.presentation.screens.search

import com.example.recipeapp.domain.model.RecipeSummaryUiModel

object SearchContract {

    data class SearchState(
        val isLoading: Boolean = false,
        val searchResult: List<RecipeSummaryUiModel> = emptyList(),
        val searchQuery: String = "",
        val favoriteRecipeIds: Set<Int> = emptySet(),
        val errorMessage: String? = null
    )


    sealed interface SearchIntent {

        data class OnSearchQueryChanged(
            val query: String
        ) : SearchIntent

        data class OnRecipeClicked(
            val recipeId: Int
        ) : SearchIntent

        data class OnFavoriteClicked(
            val recipe: RecipeSummaryUiModel
        ) : SearchIntent
    }

    sealed interface SearchEffect {

        data class NavigateToRecipeDetails(
            val recipeId: Int
        ) : SearchEffect

        data class ShowMessage(
            val message: String? = null
        ) : SearchEffect


    }


}