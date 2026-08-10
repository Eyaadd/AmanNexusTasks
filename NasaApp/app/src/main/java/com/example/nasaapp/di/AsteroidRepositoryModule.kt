package com.example.nasaapp.di

import com.example.nasaapp.data.repository.AsteroidRepository
import com.example.nasaapp.data.repository.AsteroidRepositoryImpl
import org.koin.dsl.module

val asteroidRepositoryModule = module {

    single<AsteroidRepository>(qualifier = asteroidRepositoryImpNamed) {
        AsteroidRepositoryImpl(
            ktorApi = get(ktorApiNamed),
            asteroidDao = get(asteroidDaoNamed)
        )
    }
}
