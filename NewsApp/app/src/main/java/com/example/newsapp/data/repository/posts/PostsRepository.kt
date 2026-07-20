package com.example.newsapp.data.repository.posts

import com.example.newsapp.data.source.remote.models.Post

interface PostsRepository {
    suspend fun getPosts() : List<Post>
}