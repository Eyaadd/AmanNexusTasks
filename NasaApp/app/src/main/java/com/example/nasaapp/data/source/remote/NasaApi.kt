package com.example.nasaapp.data.source.remote

import com.example.nasaapp.BuildConfig
import com.example.nasaapp.data.source.remote.dtos.AsteroidsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroidsInfo(
        @Query("api_key") apiKey : String = BuildConfig.NASA_API_KEY
    ) : AsteroidsDto
}