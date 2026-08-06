package com.example.recipeapp.data.source.remote.api

import com.example.recipeapp.data.source.remote.dtos.RandomRecipesResponseDto
import com.example.recipeapp.data.source.remote.dtos.RecipeDto
import com.example.recipeapp.data.source.remote.dtos.SearchRecipesResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RecipeApiImpl(
    private val httpClient: HttpClient
) : RecipeApi {

    override suspend fun getRandomRecipes(
        number: Int
    ): RandomRecipesResponseDto {
        return httpClient.get("recipes/random") {
            parameter("number", number)
        }.body()
    }

    override suspend fun searchRecipes(
        query: String, number: Int, offset: Int
    ): SearchRecipesResponseDto {
        return httpClient.get("recipes/complexSearch") {
            parameter("query", query)
            parameter("number", number)
            parameter("offset", offset)
            parameter("addRecipeInformation", true)
        }.body()
    }

    override suspend fun getRecipeDetails(
        recipeId: Int
    ): RecipeDto {
        return httpClient.get("recipes/$recipeId/information") {
            parameter("includeNutrition", false)
        }.body()
    }

    override suspend fun getPopularCategoryRecipes(
        query: String?, type: String?, number: Int
    ): SearchRecipesResponseDto {
        return httpClient.get("recipes/complexSearch") {
            query?.takeIf { it.isNotBlank() }?.let { parameter("query", it) }

            type?.takeIf { it.isNotBlank() }?.let { parameter("type", it) }

            parameter("number", number)
            parameter("addRecipeInformation", true)
            parameter("fillIngredients", true)
        }.body()
    }

    override suspend fun getRecentRecipes(
        number: Int, offset: Int
    ): SearchRecipesResponseDto {
        return httpClient.get("recipes/complexSearch") {
            parameter("number", number)
            parameter("offset", offset)
            parameter("sort", "popularity")
            parameter("sortDirection", "desc")
            parameter("addRecipeInformation", true)
        }.body()
    }

    override suspend fun searchForRecipe(query: String?): SearchRecipesResponseDto {
        return httpClient.get("recipes/complexSearch") {
            query?.takeIf { it.isNotBlank() }?.let {
                parameter("query", it)
            }
            parameter("number", 20)
            parameter("addRecipeInformation", true)
        }.body()
    }
}