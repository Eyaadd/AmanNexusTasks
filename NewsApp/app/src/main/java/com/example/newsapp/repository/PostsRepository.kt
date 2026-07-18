package com.example.newsapp.repository

import com.example.newsapp.ui.home.models.Post

interface PostsRepository {
    suspend fun getPosts(): List<Post>
}