package com.example.newsapp.data.repository.posts

import com.example.newsapp.data.source.remote.PostApi
import com.example.newsapp.data.source.remote.mapper.toDomain
import com.example.newsapp.data.source.remote.models.Post

class PostsRepositoryImp(private val api: PostApi) : PostsRepository {
    override suspend fun getPosts(): List<Post> {
        val response = api.getPosts()
        return response.posts.map {
            it.toDomain()
        }
    }
}