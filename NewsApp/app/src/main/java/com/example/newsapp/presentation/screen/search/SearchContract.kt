package com.example.newsapp.presentation.screen.search

import com.example.newsapp.data.source.remote.models.Post

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