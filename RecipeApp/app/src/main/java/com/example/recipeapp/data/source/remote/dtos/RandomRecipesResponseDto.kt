package com.example.recipeapp.data.source.remote.dtos


import kotlinx.serialization.Serializable

@Serializable
data class RandomRecipesResponseDto(
    val recipes: List<RecipeDto> = emptyList()
)