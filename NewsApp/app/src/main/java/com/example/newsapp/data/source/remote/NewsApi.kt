package com.example.newsapp.data.source.remote

import com.example.newsapp.data.source.remote.dtos.PostsResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("posts")
    suspend fun getPosts(
        @Query("limit") limit: Int = 30,
        @Query("skip") skip: Int = 0
    ): PostsResponseDTO
}