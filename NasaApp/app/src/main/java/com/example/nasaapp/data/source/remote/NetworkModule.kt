package com.example.nasaapp.data.source.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    private const val BASE_URL = "https://api.nasa.gov/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()


    private val retroFit =
        Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(
            GsonConverterFactory.create()
        ).build()

    val nasaApi: NasaApi = retroFit.create(NasaApi::class.java)

}