package com.example.nasaapp.di

import org.koin.core.qualifier.named

val ktorNetworkClientNamed = named("KtorNetworkClient")

val ktorApiNamed =  named("KtorApi")

val asteroidRepositoryImpNamed = named("AsteroidRepositoryImp")

val roomDatabaseNamed = named("RoomDatabase")

val asteroidDaoNamed = named("AsteroidDao")

val homeViewModelNamed = named("homeViewModel")

val asteroidRefreshWorkerNamed = named("asteroidRefreshWorker")