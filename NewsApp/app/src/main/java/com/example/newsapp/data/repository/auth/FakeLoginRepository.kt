package com.example.newsapp.data.repository.auth

import kotlinx.coroutines.delay

class FakeAuthRepository : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Boolean {

        delay(2000)

        return email == "admin@test.com" &&
                password == "123456"
    }
}