package com.example.recipeapp.data.model

data class RecipeSummaryUiModel(
    val image: String?,
    val rating : Double? = null,
    val readyInMinutes: Int? = null,
    val recipeName: String,
    val sourceName: String?
)
