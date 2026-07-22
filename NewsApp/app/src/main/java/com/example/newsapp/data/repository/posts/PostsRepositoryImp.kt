package com.example.newsapp.data.repository.posts

import com.example.newsapp.data.source.remote.NewsApi
import com.example.newsapp.data.source.remote.mapper.toDomain
import com.example.newsapp.data.models.ArticlesModel

class PostsRepositoryImp(private val api: NewsApi) : PostsRepository {
    override suspend fun getPosts(): List<ArticlesModel> {
        val response = api.getPosts()
        return response.posts.map {
            it.toDomain()
        }
    }
}