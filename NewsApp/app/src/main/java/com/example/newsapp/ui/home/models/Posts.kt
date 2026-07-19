package com.example.newsapp.ui.home.models

data class Post(
    val image: Int,
    val category: String,
    val title: String,
    val authorIcon: Int,
    val author: String,
    val publishDate: String
)