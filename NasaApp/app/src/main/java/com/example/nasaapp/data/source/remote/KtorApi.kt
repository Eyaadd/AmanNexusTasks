package com.example.nasaapp.data.source.remote

import com.example.nasaapp.data.source.remote.dtos.AsteroidsDto


import com.example.nasaapp.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class KtorApi(
    private val client: HttpClient
) {

    suspend fun getAsteroidsInfo(
        apiKey: String = BuildConfig.NASA_API_KEY
    ): AsteroidsDto {
        return client
            .get("/neo/rest/v1/feed") {
                parameter("api_key", apiKey)
            }
            .body()
    }
}