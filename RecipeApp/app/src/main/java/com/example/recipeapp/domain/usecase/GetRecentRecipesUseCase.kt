package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.model.RecipeSummaryUiModel
import com.example.recipeapp.domain.repository.RecipeRepository

class GetRecentRecipesUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(): Result<List<RecipeSummaryUiModel>> =
        recipeRepository.getRecentRecipes()
}