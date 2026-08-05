package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.model.RecipeSummaryUiModel
import com.example.recipeapp.domain.repository.RecipeRepository

class GetRecipesUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(): Result<List<RecipeSummaryUiModel>> =
        recipeRepository.getRecipes()
}