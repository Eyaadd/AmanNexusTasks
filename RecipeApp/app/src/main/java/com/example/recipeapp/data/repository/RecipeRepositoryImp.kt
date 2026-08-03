package com.example.recipeapp.data.repository

import com.example.recipeapp.data.model.RecipeSummaryUiModel
import com.example.recipeapp.data.source.remote.api.RecipeApi
import com.example.recipeapp.data.source.remote.mapper.toRecipeSummaryUiModel

class RecipeRepositoryImp(
    private val recipeApi: RecipeApi
) : RecipeRepository {
    override suspend fun getRecipes(): List<RecipeSummaryUiModel> {
        return recipeApi.getRandomRecipes().recipes.map {
            it.toRecipeSummaryUiModel()
        }
    }
}


