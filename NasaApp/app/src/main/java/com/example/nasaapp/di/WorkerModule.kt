package com.example.nasaapp.di

import com.example.nasaapp.data.worker.AsteroidRefreshWorker
import org.koin.androidx.workmanager.dsl.worker
import org.koin.core.qualifier.named
import org.koin.dsl.module

val workerModule = module {

    worker(qualifier = asteroidRefreshWorkerNamed){
        AsteroidRefreshWorker(
            appContext = get(),
            workerParameters = get(),
            repository = get()
        )
    }
}