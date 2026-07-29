package com.example.nasaapp.data.repository


import com.example.nasaapp.data.source.local.dao.AsteroidDao
import com.example.nasaapp.data.source.remote.NasaApi
import com.example.nasaapp.data.source.remote.mapper.toEntity
import com.example.nasaapp.presentation.mapper.toUiModel
import com.example.nasaapp.data.model.AsteroidUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AsteroidRepositoryImpl(
    private val nasaApi: NasaApi,
    private val asteroidDao: AsteroidDao
) : AsteroidRepository {

    override fun observeAsteroids(): Flow<List<AsteroidUiModel>> {
        return asteroidDao.observeAsteroids()
            .map { entities ->
                entities.map { entity ->
                    entity.toUiModel()
                }
            }
    }

    override suspend fun refreshAsteroids() {
        val response = nasaApi.getAsteroidsInfo()

        val asteroids = response.nearEarthObjects
            .values
            .flatten()
            .mapNotNull { dto ->
                dto.toEntity()
            }

        asteroidDao.deleteAllAsteroids()
        asteroidDao.insertAsteroids(asteroids)
    }
}