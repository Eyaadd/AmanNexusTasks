package com.example.recipeapp.di

import com.example.recipeapp.domain.usecase.AddRecipeToFavoriteUseCase
import com.example.recipeapp.domain.usecase.GetPopularCategoryRecipesUseCase
import com.example.recipeapp.domain.usecase.GetRecentRecipesUseCase
import com.example.recipeapp.domain.usecase.GetRecipesUseCase
import com.example.recipeapp.domain.usecase.ObserveFavoriteRecipesUseCase
import com.example.recipeapp.domain.usecase.RemoveRecipeFromFavoritesUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetRecipesUseCase(get()) }

    factory { GetPopularCategoryRecipesUseCase(get()) }

    factory { GetRecentRecipesUseCase(get()) }


    factory { AddRecipeToFavoriteUseCase(get()) }

    factory { RemoveRecipeFromFavoritesUseCase(get()) }

    factory { ObserveFavoriteRecipesUseCase(get()) }

}