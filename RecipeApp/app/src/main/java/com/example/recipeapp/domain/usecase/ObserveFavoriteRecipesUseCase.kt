package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveFavoriteRecipesUseCase(
    private val recipeRepository: RecipeRepository
) {
    operator fun invoke(): Flow<Set<Int>> =
        recipeRepository
            .observeFavoriteRecipes()
            .map { recipes ->
                recipes.map { recipe -> recipe.id }.toSet()
            }
}