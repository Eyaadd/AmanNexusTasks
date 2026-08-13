package com.example.newsapp.data.models

data class ArticlesModel(
    val id: Int,
    val title: String,
    val body: String,
    val tags: List<String>,
    val likeCount: Int,
    val dislikeCount: Int,
    val views: Int
)

