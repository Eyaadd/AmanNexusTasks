package com.example.recipeapp.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoute {

    @Serializable
    data object Login : AppRoute

    @Serializable
    data object SignUp : AppRoute

    @Serializable
    data object Home : AppRoute

    @Serializable
    data object Search : AppRoute

    @Serializable
    data object Favorites : AppRoute

    @Serializable
    data object Profile : AppRoute

    @Serializable
    data class RecipeDetails(
        val recipeId: Int
    ) : AppRoute
}
