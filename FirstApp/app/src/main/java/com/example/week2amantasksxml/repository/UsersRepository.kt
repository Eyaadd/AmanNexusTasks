package com.example.week2amantasksxml.repository

import androidx.lifecycle.LiveData
import com.example.week2amantasksxml.models.User

interface UsersRepository {

    suspend fun fetchUsers() : List<User>
}