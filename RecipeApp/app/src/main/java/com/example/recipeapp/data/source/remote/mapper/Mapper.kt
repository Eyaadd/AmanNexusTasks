package com.example.recipeapp.data.source.remote.mapper

import com.example.recipeapp.domain.model.DirectionUiModel
import com.example.recipeapp.domain.model.RecipeDetailsUiModel
import com.example.recipeapp.domain.model.RecipeSummaryUiModel
import com.example.recipeapp.data.source.remote.dtos.RecipeDto
import com.example.recipeapp.data.source.local.entity.FavoriteRecipeEntity

fun RecipeDto.toRecipeSummaryUiModel() : RecipeSummaryUiModel {
    return RecipeSummaryUiModel(
        id = id,
        imageUrl = image,
        rating = spoonacularScore,
        readyInMinutes = readyInMinutes,
        recipeName = title,
        sourceName = sourceName
    )
}

fun RecipeDetailsUiModel.toRecipeSummaryUiModel(): RecipeSummaryUiModel {
    return RecipeSummaryUiModel(
        id = id,
        imageUrl = imageUrl,
        rating = rating,
        readyInMinutes = readyInMinutes,
        recipeName = title,
        sourceName = sourceName
    )
}
fun RecipeDto.toRecipeDetailsUiModel(): RecipeDetailsUiModel {
    return RecipeDetailsUiModel(
        id = id,
        imageUrl = image,
        title = title,
        description = summary.orEmpty(),
        rating = spoonacularScore
            ?.div(20.0),
        readyInMinutes = readyInMinutes,
        servings = servings,
        sourceName = sourceName,
        ingredients = extendedIngredients.map { ingredient ->
            ingredient.original
        },
        directions = analyzedInstructions
            .flatMap { instruction ->
                instruction.steps
            }
            .map { step ->
                DirectionUiModel(
                    number = step.number,
                    step = step.step
                )
            },
        sourceUrl = sourceUrl
    )
}

fun RecipeSummaryUiModel.toFavoriteRecipeEntity(): FavoriteRecipeEntity {
    return FavoriteRecipeEntity(
        id = id,
        imageUrl = imageUrl,
        title = recipeName,
        readyInMinutes = readyInMinutes,
        rating = rating,
        sourceName = sourceName
    )
}

fun FavoriteRecipeEntity.toRecipeSummaryUiModel(): RecipeSummaryUiModel {
    return RecipeSummaryUiModel(
        id = id,
        imageUrl = imageUrl,
        rating = rating,
        readyInMinutes = readyInMinutes,
        recipeName = title,
        sourceName = sourceName
    )
}