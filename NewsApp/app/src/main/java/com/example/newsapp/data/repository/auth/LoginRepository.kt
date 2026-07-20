package com.example.newsapp.data.repository.auth

interface LoginRepository {

    suspend fun login(
        email: String,
        password: String
    ): Boolean
}