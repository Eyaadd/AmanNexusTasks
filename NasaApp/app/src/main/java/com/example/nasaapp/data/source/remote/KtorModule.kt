package com.example.nasaapp.data.source.remote


import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorNetworkModule {

    val client: HttpClient by lazy {
        HttpClient(Android) {

            expectSuccess = true

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }

            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = NASA_HOST
                }
            }

            install(HttpTimeout) {
                connectTimeoutMillis = 15_000
                requestTimeoutMillis = 30_000
                socketTimeoutMillis = 30_000
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d(KTOR_LOG_TAG, message)
                    }
                }

                level = LogLevel.ALL
            }
        }
    }

    private const val NASA_HOST = "api.nasa.gov"
    private const val KTOR_LOG_TAG = "KtorClient"
}