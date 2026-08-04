package com.example.recipeapp

import android.app.Application
import com.example.recipeapp.di.databaseModule
import com.example.recipeapp.di.ktorModule
import com.example.recipeapp.di.repositoryModule
import com.example.recipeapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RecipeApplication : Application() {

    override fun onCreate() {
        startKoin {
            androidContext(this@RecipeApplication)
            modules(ktorModule, repositoryModule, viewModelModule, databaseModule)
        }
    }
}