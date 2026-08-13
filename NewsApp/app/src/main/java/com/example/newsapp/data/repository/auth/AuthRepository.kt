package com.example.newsapp.data.repository.auth

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): Boolean
}