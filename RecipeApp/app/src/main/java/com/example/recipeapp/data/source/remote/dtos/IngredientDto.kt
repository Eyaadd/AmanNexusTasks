package com.example.recipeapp.data.source.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class IngredientDto(
    val id: Int? = null,
    val name: String,
    val original: String,
    val image: String? = null,

)