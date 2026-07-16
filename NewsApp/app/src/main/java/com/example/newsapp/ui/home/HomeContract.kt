package com.example.newsapp.ui.home

import com.example.newsapp.ui.home.models.Post

object HomeContract {

    data class HomeState(
        val posts: List<Post> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val selectedRoute: String = "home"
    )

    sealed interface HomeIntent {

        data object LoadPosts : HomeIntent

        data object RetryClicked : HomeIntent

        data class BottomNavItemClicked(
            val route: String
        ) : HomeIntent
    }

    sealed interface HomeEffect {

        data class ShowError(
            val message: String
        ) : HomeEffect

        data object NavigateToSearch : HomeEffect
    }
}