package com.example.recipeapp.di

import com.example.recipeapp.domain.repository.RecipeRepository
import com.example.recipeapp.data.repository.RecipeRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<RecipeRepository> {
        RecipeRepositoryImpl(
            recipeApi = get(),
            favoriteRecipeDao = get()
        )
    }
}