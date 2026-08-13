package com.example.newsapp.data.source.remote.dtos

data class PostsResponseDTO(
    val posts: List<PostsDTO>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

