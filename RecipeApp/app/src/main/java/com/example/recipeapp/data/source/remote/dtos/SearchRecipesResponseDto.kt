package com.example.recipeapp.data.source.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class SearchRecipesResponseDto(
    val results: List<RecipeDto> = emptyList(),
    val offset: Int = 0,
    val number: Int = 0,
    val totalResults: Int = 0
)