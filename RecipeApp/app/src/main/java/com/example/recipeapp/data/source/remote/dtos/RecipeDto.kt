package com.example.recipeapp.data.source.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    val id: Int,
    val image: String? = null,
    val title: String,

    // Home and details
    val readyInMinutes: Int? = null,
    val spoonacularScore: Double? = null,
    val sourceName: String? = null,

    // Details
    val summary: String? = null,
    val servings: Int? = null,

    val extendedIngredients: List<IngredientDto> = emptyList(),
    val sourceUrl: String? = null,
    val analyzedInstructions: List<AnalyzedInstructionDto> = emptyList()

)