package com.example.newsapp.data.repository

import com.example.newsapp.ui.login.repository.LoginRepository
import kotlinx.coroutines.delay

class FakeLoginRepository : LoginRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Boolean {

        delay(2000)

        return email == "admin@test.com" &&
                password == "123456"
    }
}