package com.example.recipeapp.di

import android.util.Log
import com.example.recipeapp.BuildConfig
import com.example.recipeapp.data.source.remote.api.RecipeApi
import com.example.recipeapp.data.source.remote.api.RecipeApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val ktorModule = module {

    single<HttpClient> {
        HttpClient(Android) {

            expectSuccess = true

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        explicitNulls = false
                    }
                )
            }

            install(HttpTimeout) {
                connectTimeoutMillis = 15_000
                requestTimeoutMillis = 30_000
                socketTimeoutMillis = 30_000
            }

            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.spoonacular.com"
                }

                url.parameters.append(
                    name = "apiKey",
                    value = BuildConfig.SPOONACULAR_API_KEY
                )
            }

            if (BuildConfig.DEBUG) {
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            Log.d("RecipeKtorClient", message)
                        }
                    }

                    level = LogLevel.INFO

                    sanitizeHeader { header ->
                        header.equals(
                            other = "Authorization",
                            ignoreCase = true
                        )
                    }
                }
            }
        }
    }

    single<RecipeApi> {
        RecipeApiImpl(
            httpClient = get()
        )
    }
}