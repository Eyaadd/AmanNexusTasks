package com.example.newsapp.data.source.remote.dtos

data class PostDTO(
    val image: Int,
    val category: String,
    val title: String,
    val authorIcon: Int,
    val author: String,
    val publishDate: String

)