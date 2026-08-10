package com.example.recipeapp.di

import androidx.room.Room
import com.example.recipeapp.data.source.local.dao.FavoriteRecipeDao
import com.example.recipeapp.data.source.local.database.RecipeDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<RecipeDatabase>{
        Room.databaseBuilder(
            androidContext(),
            RecipeDatabase::class.java,
            "recipe_database"
        ).build()
    }
    single<FavoriteRecipeDao>{
        get<RecipeDatabase>().favoriteRecipeDao()
    }
}