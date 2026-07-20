package com.example.newsapp.data.source.remote.dtos

data class PostsDTO(
    val id: Int,
    val title: String,
    val body: String,
    val tags: List<String>,
    val reactions: ReactionsDTO,
    val views: Int,
    val userID: Int,
)
