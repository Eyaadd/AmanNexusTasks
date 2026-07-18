package com.example.newsapp.ui.search

import com.example.newsapp.ui.home.models.Post

object SearchContract {
    data class SearchState(
        val query: String = "",
        val posts: List<Post> = emptyList(),
        val isLoading: Boolean = false,
        val isLoaded: Boolean = false,
        val isError: Boolean = false
    )

    sealed interface SearchIntent {

        data class OnSearchQueryChanged(
            val query: String
        ) : SearchIntent

    }


    sealed interface SearchEffect {

        data object NavigateToHomePage : SearchEffect
    }

}