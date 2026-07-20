package com.example.newsapp.data.repository.news

import com.example.newsapp.data.source.remote.models.PostDTO

interface PostsRepository {
    suspend fun getPosts(): List<PostDTO>
}