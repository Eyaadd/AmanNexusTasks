package com.example.newsapp.data.models

data class PostModel(
    val image: Int,
    val category: String,
    val title: String,
    val authorIcon: Int,
    val author: String,
    val publishDate: String
)