package com.example.recipeapp.data.source.remote.api


import com.example.recipeapp.data.source.remote.dtos.RandomRecipesResponseDto
import com.example.recipeapp.data.source.remote.dtos.RecipeDto
import com.example.recipeapp.data.source.remote.dtos.SearchRecipesResponseDto

interface RecipeApi {

    suspend fun getRandomRecipes(
        number: Int = 10
    ): RandomRecipesResponseDto

    suspend fun searchRecipes(
        query: String,
        number: Int = 10,
        offset: Int = 0
    ): SearchRecipesResponseDto

    suspend fun getRecipeDetails(
        recipeId: Int
    ): RecipeDto

    suspend fun getPopularCategoryRecipes(
        query: String?,
        type: String?,
        number: Int = 5
    ): SearchRecipesResponseDto

    suspend fun getRecentRecipes(
        number: Int = 8,
        offset: Int = 5
    ): SearchRecipesResponseDto
}