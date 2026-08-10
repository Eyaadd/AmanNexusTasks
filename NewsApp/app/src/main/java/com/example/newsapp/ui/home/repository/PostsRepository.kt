package com.example.newsapp.ui.home.repository

import com.example.newsapp.ui.home.models.Post

interface PostsRepository {
    suspend fun getPosts(): List<Post>
}