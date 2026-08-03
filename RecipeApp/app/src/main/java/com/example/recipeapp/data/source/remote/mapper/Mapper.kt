package com.example.recipeapp.data.source.remote.mapper

import com.example.recipeapp.data.model.RecipeSummaryUiModel
import com.example.recipeapp.data.source.remote.dtos.RecipeDto

fun RecipeDto.toRecipeSummaryUiModel() : RecipeSummaryUiModel {
    return RecipeSummaryUiModel(
        image = image,
        rating = spoonacularScore,
        readyInMinutes = readyInMinutes,
        recipeName = title,
        sourceName = sourceName
    )
}