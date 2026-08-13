package com.example.newsapp.data.repository.posts

import com.example.newsapp.data.models.ArticlesModel

interface PostsRepository {
    suspend fun getPosts() : List<ArticlesModel>
}