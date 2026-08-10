package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.repository.RecipeRepository

class SearchForRecipeUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(query: String?) = recipeRepository.searchForRecipe(query)
}