package com.example.newsapp.presentation.screen.post

import com.example.newsapp.data.models.ArticlesModel

object PostContract {

    data class PostState(
        val isLoading: Boolean = false,
        val success: List<ArticlesModel> = emptyList(),
        val errorMessage: String? = "",
        val onError: Boolean = false,
    )

}