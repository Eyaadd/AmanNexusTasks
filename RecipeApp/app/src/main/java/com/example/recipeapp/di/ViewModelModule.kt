package com.example.recipeapp.di


import com.example.recipeapp.presentation.screens.details.DetailsViewModel
import com.example.recipeapp.presentation.screens.favorites.FavoritesViewModel
import com.example.recipeapp.presentation.screens.home.HomeViewModel
import com.example.recipeapp.presentation.screens.profile.ProfileViewModel
import com.example.recipeapp.presentation.screens.search.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        HomeViewModel(
            getRecipesUseCase = get(),
            getPopularCategoryRecipesUseCase = get(),
            getRecentRecipesUseCase = get(),
            observeFavoriteRecipesUseCase = get(),
            removeRecipeFromFavoritesUseCase = get(),
            addRecipeToFavoriteUseCase = get(),
        )
    }
    viewModel {
        FavoritesViewModel(
            recipeRepository = get()
        )
    }
    viewModel { parameters ->
        DetailsViewModel(
            recipeId = parameters.get(),
            getRecipeDetailsUseCase = get(),
            observeIsFavoriteUseCase = get(),
            addRecipeToFavoriteUseCase = get(),
            removeRecipeFromFavoritesUseCase = get()
        )
    }

    viewModel {
        SearchViewModel(
            searchForRecipeUseCase = get(),
            addRecipeToFavoriteUseCase = get(),
            removeRecipeFromFavoritesUseCase = get(),
            observeFavoriteRecipesUseCase = get(),
        )
    }

    viewModel { ProfileViewModel() }

}
