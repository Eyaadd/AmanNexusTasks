package com.example.nasaapp.di

import android.util.Log
import com.example.nasaapp.data.source.remote.KtorApi
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

    single<HttpClient>(qualifier = ktorNetworkClientNamed) {
        val nasaHost = "api.nasa.gov"
        val ktorLogTag = "KtorClient"

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
                        host = nasaHost
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
                            Log.d(ktorLogTag, message)
                        }
                    }

                    level = LogLevel.ALL
                }
            }



    }
    single<KtorApi>(qualifier = ktorApiNamed) {
        KtorApi(
            client = get(ktorNetworkClientNamed)
        )
    }

}