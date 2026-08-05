package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.model.RecipeCategory
import com.example.recipeapp.domain.model.RecipeSummaryUiModel
import com.example.recipeapp.domain.repository.RecipeRepository

class GetPopularCategoryRecipesUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(
        category: RecipeCategory
    ): Result<List<RecipeSummaryUiModel>> =
        recipeRepository.getPopularCategoryRecipes(category)
}