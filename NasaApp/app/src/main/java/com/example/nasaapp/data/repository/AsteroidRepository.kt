package com.example.nasaapp.data.repository



import com.example.nasaapp.data.model.AsteroidUiModel
import kotlinx.coroutines.flow.Flow

interface AsteroidRepository {

    fun observeAsteroids(): Flow<List<AsteroidUiModel>>

    suspend fun refreshAsteroids()
}