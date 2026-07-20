package com.example.newsapp.presentation.screen.post

import com.example.newsapp.data.source.remote.models.Post

object PostContract {

    data class PostState(
        val isLoading: Boolean = false,
        val success: List<Post> = emptyList(),
        val errorMessage: String? = "",
        val onError: Boolean = false,
    )

    sealed interface Intent{

    }

    sealed interface Effect{

    }
}