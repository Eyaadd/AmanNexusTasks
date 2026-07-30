package com.example.nasaapp

import android.app.Application
import android.content.res.Configuration
import com.example.nasaapp.di.asteroidRepositoryModule
import com.example.nasaapp.di.databaseModule
import com.example.nasaapp.di.ktorModule
import com.example.nasaapp.di.viewModelModule
import com.example.nasaapp.di.workerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class NasaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NasaApplication)
            workManagerFactory()
            modules(
                ktorModule, viewModelModule, databaseModule, asteroidRepositoryModule, workerModule
            )
        }
    }
}