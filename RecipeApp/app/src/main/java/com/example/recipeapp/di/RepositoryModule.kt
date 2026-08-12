package com.example.recipeapp.di

import com.example.recipeapp.domain.repository.RecipeRepository
import com.example.recipeapp.data.repository.RecipeRepositoryImpl
import com.example.recipeapp.data.repository.AuthRepositoryImpl
import com.example.recipeapp.domain.repository.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<RecipeRepository> {
        RecipeRepositoryImpl(
            recipeApi = get(),
            favoriteRecipeDao = get()
        )
    }

    single<AuthRepository> {
        AuthRepositoryImpl(firebaseAuth = get())
    }
}
