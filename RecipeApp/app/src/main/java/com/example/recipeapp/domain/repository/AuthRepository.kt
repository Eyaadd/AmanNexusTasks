package com.example.recipeapp.domain.repository

interface AuthRepository {
    suspend fun signUp(
        email: String,
        password: String
    ): Result<Unit>

    suspend fun login(
        email: String,
        password: String
    ): Result<Unit>

    fun logout()

    fun isLoggedIn(): Boolean
}