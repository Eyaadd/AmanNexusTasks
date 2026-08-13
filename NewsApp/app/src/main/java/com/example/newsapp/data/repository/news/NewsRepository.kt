package com.example.newsapp.data.repository.news

import com.example.newsapp.data.source.remote.dtos.ArticlesDTO

interface NewsRepository {
    suspend fun getPosts(): List<ArticlesDTO>
}