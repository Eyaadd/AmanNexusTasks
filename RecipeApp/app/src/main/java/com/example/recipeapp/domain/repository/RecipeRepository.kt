package com.example.recipeapp.domain.repository

import com.example.recipeapp.domain.model.RecipeCategory
import com.example.recipeapp.domain.model.RecipeDetailsUiModel
import com.example.recipeapp.domain.model.RecipeSummaryUiModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun getRecipes(): Result<List<RecipeSummaryUiModel>>

    suspend fun getPopularCategoryRecipes(
        category: RecipeCategory
    ): Result<List<RecipeSummaryUiModel>>

    suspend fun getRecentRecipes(): Result<List<RecipeSummaryUiModel>>

    fun observeFavoriteRecipes(): Flow<List<RecipeSummaryUiModel>>

    fun observeIsFavorite(
        recipeId: Int
    ): Flow<Boolean>

    suspend fun addRecipeToFavorites(
        recipe: RecipeSummaryUiModel
    ): Result<Unit>

    suspend fun removeRecipeFromFavorites(
        recipeId: Int
    ): Result<Unit>

    suspend fun getRecipeDetails(
        recipeId: Int
    ): Result<RecipeDetailsUiModel>

    suspend fun searchForRecipe(
        query: String?
    ):Result<List<RecipeSummaryUiModel>>
}