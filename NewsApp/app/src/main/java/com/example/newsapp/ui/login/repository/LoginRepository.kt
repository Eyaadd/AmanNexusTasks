package com.example.newsapp.ui.login.repository


interface LoginRepository {

    suspend fun login(
        email: String,
        password: String
    ): Boolean
}