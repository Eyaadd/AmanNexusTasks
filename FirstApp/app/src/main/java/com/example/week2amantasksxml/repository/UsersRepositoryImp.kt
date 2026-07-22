package com.example.week2amantasksxml.repository

import com.example.week2amantasksxml.models.User
import kotlinx.coroutines.delay

class UsersRepositoryImp : UsersRepository {
    override suspend fun fetchUsers(): List<User> {
        delay(2000)
        return listOf(
            User(
                id = 1,
                name = "Eyad",
                email = "eyadyehia07@gmail.com"
            ), User(
                id = 2,
                name = "Ahmed",
                email = "ahmed.hassan@gmail.com"
            ),
            User(
                id = 3,
                name = "Sara",
                email = "sara.ali@gmail.com"
            ),
            User(
                id = 4,
                name = "Mohamed",
                email = "mohamed.ibrahim@gmail.com"
            ),
            User(
                id = 5,
                name = "Mariam",
                email = "mariam.salem@gmail.com"
            ),
            User(
                id = 6,
                name = "Youssef",
                email = "youssef.adel@gmail.com"
            )
        )
    }
}