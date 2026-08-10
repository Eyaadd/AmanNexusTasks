package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.repository.RecipeRepository

class GetRecipeDetailsUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(recipeId: Int) = recipeRepository.getRecipeDetails(recipeId)
}