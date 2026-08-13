package com.example.newsapp.data.source.remote.dtos

// ignore
data class ArticlesDTO(
    val image: Int,
    val category: String,
    val title: String,
    val authorIcon: Int,
    val author: String,
    val publishDate: String
)