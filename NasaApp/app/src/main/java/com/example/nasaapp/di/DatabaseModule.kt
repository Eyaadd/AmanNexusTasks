package com.example.nasaapp.di

import androidx.room.Room
import com.example.nasaapp.data.source.local.dao.AsteroidDao
import com.example.nasaapp.data.source.local.database.AsteroidDatabase
import org.koin.dsl.module

val databaseModule = module {
    single<AsteroidDatabase>(qualifier = roomDatabaseNamed) {
        Room.databaseBuilder(
            context = get(), klass = AsteroidDatabase::class.java, name = "asteroid_database"
        ).build()
    }
    single<AsteroidDao>(qualifier = asteroidDaoNamed){
        get<AsteroidDatabase>(roomDatabaseNamed).asteroidDao()
    }
}