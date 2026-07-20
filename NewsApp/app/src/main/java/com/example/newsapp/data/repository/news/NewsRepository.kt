package com.example.newsapp.data.repository.news

import com.example.newsapp.data.source.remote.dtos.PostDTO

interface NewsRepository {
    suspend fun getPosts(): List<PostDTO>
}