package com.example.recipeapp.domain.model

data class RecipeSummaryUiModel(
    val id: Int,
    val imageUrl: String?,
    val rating : Double? = null,
    val readyInMinutes: Int? = null,
    val recipeName: String,
    val sourceName: String?
)
