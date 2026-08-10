package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.repository.RecipeRepository

class RemoveRecipeFromFavoritesUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(
        recipeId: Int
    ): Result<Unit> =
        recipeRepository.removeRecipeFromFavorites(recipeId)
}