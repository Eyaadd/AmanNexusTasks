package com.example.recipeapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favorite_recipes")
data class FavoriteRecipeEntity(
    @PrimaryKey
    val id: Int,
    val imageUrl: String?,
    val title: String,
    val readyInMinutes: Int?,
    val rating: Double?,
    val sourceName: String?,
    )
