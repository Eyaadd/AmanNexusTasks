package com.example.newsapp.ui.home.viewmodel

import com.example.newsapp.ui.home.models.Post

sealed class PostsUiState {

    data object Loading : PostsUiState()

    data class Success(
        val posts: List<Post>
    ) : PostsUiState()

    data class Error(
        val message: String
    ) : PostsUiState()
}