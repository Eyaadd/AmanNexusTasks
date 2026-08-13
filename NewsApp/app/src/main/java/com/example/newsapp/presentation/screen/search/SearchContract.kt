package com.example.newsapp.presentation.screen.search

import com.example.newsapp.presentation.uimodel.PostModel

object SearchContract {
    data class SearchState(
        val query: String = "",
        val postModel: List<PostModel> = emptyList(),
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