package com.example.recipeapp.data.model

data class RecipeDetailsUiModel(
    val id: Int,
    val imageUrl: String?,
    val title: String,
    val description: String,
    val rating: Double?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val sourceName: String?,
    val ingredients: List<String>,
    val directions: List<DirectionUiModel>,
    val sourceUrl: String?
)