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
        return httpClient
            .get("recipes/random") {
                parameter("number", number)
            }
            .body()
    }

    override suspend fun searchRecipes(
        query: String,
        number: Int,
        offset: Int
    ): SearchRecipesResponseDto {
        return httpClient
            .get("recipes/complexSearch") {
                parameter("query", query)
                parameter("number", number)
                parameter("offset", offset)
                parameter("addRecipeInformation", true)
            }
            .body()
    }

    override suspend fun getRecipeDetails(
        recipeId: Int
    ): RecipeDto {
        return httpClient
            .get("recipes/$recipeId/information") {
                parameter("includeNutrition", false)
            }
            .body()
    }
}