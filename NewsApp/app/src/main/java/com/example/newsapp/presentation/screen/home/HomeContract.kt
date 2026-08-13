package com.example.newsapp.presentation.screen.home

import com.example.newsapp.data.source.remote.models.PostDTO
import com.example.newsapp.presentation.navigation.Screen

object HomeContract {

    data class HomeState(
        val postDTOS: List<PostDTO> = emptyList(),
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