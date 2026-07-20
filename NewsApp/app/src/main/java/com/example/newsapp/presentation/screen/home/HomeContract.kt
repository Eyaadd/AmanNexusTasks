package com.example.newsapp.presentation.screen.home

import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.presentation.uimodel.PostModel

object HomeContract {

    data class HomeState(
        val postDTOS: List<PostModel> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val selectedRoute: Screen = Screen.Home
    )

    sealed interface HomeIntent {



        data class BottomNavItemClicked(
            val route: Screen
        ) : HomeIntent
    }

    sealed interface HomeEffect {

        data class ShowError(
            val message: String
        ) : HomeEffect

        data object NavigateToSearch : HomeEffect
    }
}