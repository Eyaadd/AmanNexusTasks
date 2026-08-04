package com.example.recipeapp.data.repository

import com.example.recipeapp.data.model.RecipeCategory
import com.example.recipeapp.data.model.RecipeDetailsUiModel
import com.example.recipeapp.data.model.RecipeSummaryUiModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun getRecipes(): List<RecipeSummaryUiModel>

    suspend fun getPopularCategoryRecipes(
        category: RecipeCategory
    ): List<RecipeSummaryUiModel>

    suspend fun getRecentRecipes(): List<RecipeSummaryUiModel>
    fun observeFavoriteRecipes(): Flow<List<RecipeSummaryUiModel>>

    fun observeIsFavorite(
        recipeId: Int
    ): Flow<Boolean>

    suspend fun addRecipeToFavorites(
        recipe: RecipeSummaryUiModel
    )

    suspend fun removeRecipeFromFavorites(
        recipeId: Int
    )

    suspend fun getRecipeDetails(
        recipeId: Int
    ): RecipeDetailsUiModel


}