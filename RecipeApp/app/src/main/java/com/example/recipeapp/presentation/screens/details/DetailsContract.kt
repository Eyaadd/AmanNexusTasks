package com.example.recipeapp.presentation.screens.details

import com.example.recipeapp.data.model.DetailsTab
import com.example.recipeapp.data.model.RecipeDetailsUiModel


object DetailsContract {

    data class DetailsState(
        val isLoading: Boolean = true,
        val recipe: RecipeDetailsUiModel? = null,
        val selectedTab: DetailsTab = DetailsTab.INGREDIENTS,
        val isFavorite: Boolean = false,
        val errorMessage: String? = null
    )

    sealed interface DetailsIntent {

        data object Retry : DetailsIntent

        data object OnBackClicked : DetailsIntent

        data object OnFavoriteClicked : DetailsIntent

        data class OnTabSelected(
            val tab: DetailsTab
        ) : DetailsIntent
    }

    sealed interface DetailsEffect {

        data object NavigateBack : DetailsEffect


        data class ShowMessage(
            val message: String
        ) : DetailsEffect
    }
}