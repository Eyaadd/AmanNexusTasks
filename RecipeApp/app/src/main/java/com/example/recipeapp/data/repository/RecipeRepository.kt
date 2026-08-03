package com.example.recipeapp.data.repository

import com.example.recipeapp.data.model.RecipeSummaryUiModel

interface RecipeRepository {

    suspend fun getRecipes() : List<RecipeSummaryUiModel>


}