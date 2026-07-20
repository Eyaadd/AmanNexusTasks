package com.example.newsapp.data.repository.news

import com.example.newsapp.data.source.remote.models.Post

interface PostsRepository {
    suspend fun getPosts(): List<Post>
}