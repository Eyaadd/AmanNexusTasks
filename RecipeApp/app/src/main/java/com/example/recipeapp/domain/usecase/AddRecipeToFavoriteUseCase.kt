package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.repository.RecipeRepository
import com.example.recipeapp.domain.model.RecipeSummaryUiModel

class AddRecipeToFavoriteUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(recipe: RecipeSummaryUiModel) = recipeRepository.addRecipeToFavorites(recipe)
}