package com.example.recipeapp.di

import com.example.recipeapp.domain.usecase.AddRecipeToFavoriteUseCase
import com.example.recipeapp.domain.usecase.GetPopularCategoryRecipesUseCase
import com.example.recipeapp.domain.usecase.GetRecentRecipesUseCase
import com.example.recipeapp.domain.usecase.GetRecipeDetailsUseCase
import com.example.recipeapp.domain.usecase.GetRecipesUseCase
import com.example.recipeapp.domain.usecase.ObserveFavoriteRecipesUseCase
import com.example.recipeapp.domain.usecase.ObserveIsFavoriteUseCase
import com.example.recipeapp.domain.usecase.RemoveRecipeFromFavoritesUseCase
import com.example.recipeapp.domain.usecase.SearchForRecipeUseCase
import com.example.recipeapp.domain.usecase.IsLoggedInUseCase
import com.example.recipeapp.domain.usecase.LoginUseCase
import com.example.recipeapp.domain.usecase.LogoutUseCase
import com.example.recipeapp.domain.usecase.SignUpUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetRecipesUseCase(get()) }

    factory { GetPopularCategoryRecipesUseCase(get()) }

    factory { GetRecentRecipesUseCase(get()) }


    factory { AddRecipeToFavoriteUseCase(get()) }

    factory { RemoveRecipeFromFavoritesUseCase(get()) }

    factory { ObserveFavoriteRecipesUseCase(get()) }

    factory { ObserveIsFavoriteUseCase(get()) }

    factory { GetRecipeDetailsUseCase(get()) }

    factory { SearchForRecipeUseCase(get()) }

    factory { SignUpUseCase(get()) }

    factory { LoginUseCase(get()) }

    factory { LogoutUseCase(get()) }

    factory { IsLoggedInUseCase(get()) }

}
