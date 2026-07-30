package com.example.nasaapp

import android.app.Application
import com.example.nasaapp.data.repository.AsteroidRepository
import com.example.nasaapp.data.repository.AsteroidRepositoryImpl
import com.example.nasaapp.data.source.local.database.AsteroidDatabase
import com.example.nasaapp.data.source.remote.KtorApi
import com.example.nasaapp.data.source.remote.KtorNetworkModule
import com.example.nasaapp.data.worker.AsteroidSyncScheduler
import com.example.nasaapp.presentation.screens.home.HomeViewModelFactory

class NasaApplication : Application() {

    val repository: AsteroidRepository by lazy {
        AsteroidRepositoryImpl(
            ktorApi = KtorApi(
                client = KtorNetworkModule.client
            ),
            asteroidDao = AsteroidDatabase
                .getInstance(this)
                .asteroidDao()
        )
    }

    val homeViewModelFactory by lazy {
        HomeViewModelFactory(repository)
    }
    override fun onCreate() {
        super.onCreate()

        AsteroidSyncScheduler.schedulePeriodicRefresh(
            context = this
        )
    }
}