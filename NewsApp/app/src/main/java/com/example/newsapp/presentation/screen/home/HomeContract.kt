package com.example.newsapp.presentation.screen.home

import com.example.newsapp.data.source.remote.models.Post

object HomeContract {

    data class HomeState(
        val posts: List<Post> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val selectedRoute: String = "home"
    )

    sealed interface HomeIntent {

        data object LoadPosts : HomeIntent


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