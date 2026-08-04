package com.example.recipeapp.di

import com.example.recipeapp.data.repository.RecipeRepository
import com.example.recipeapp.data.repository.RecipeRepositoryImp
import org.koin.dsl.module

val repositoryModule = module {

    single<RecipeRepository> {
        RecipeRepositoryImp(
            recipeApi = get(),
            favoriteRecipeDao = get()
        )
    }
}