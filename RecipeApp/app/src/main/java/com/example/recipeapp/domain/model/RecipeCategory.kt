package com.example.recipeapp.domain.model


enum class RecipeCategory(
    val displayName: String,
    val query: String? = null,
    val type: String? = null
) {
    VEGETABLES(
        displayName = "Vegetables",
        query = "vegetables"
    ),
    MEAT(
        displayName = "Meat",
        query = "meat"
    ),
    SALAD(
        displayName = "Salad",
        type = "salad"
    ),
    NOODLE(
        displayName = "Noodle",
        query = "noodles"
    ),
    BREAKFAST(
        displayName = "Breakfast",
        type = "breakfast"
    )
}