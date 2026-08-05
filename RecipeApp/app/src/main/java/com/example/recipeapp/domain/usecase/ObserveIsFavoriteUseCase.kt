package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class ObserveIsFavoriteUseCase(
    private val recipeRepository: RecipeRepository
) {

    operator fun invoke(
        recipeId: Int
    ) =
         recipeRepository.observeIsFavorite(recipeId)

}