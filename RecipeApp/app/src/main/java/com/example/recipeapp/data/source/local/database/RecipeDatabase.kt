package com.example.recipeapp.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipeapp.data.source.local.dao.FavoriteRecipeDao
import com.example.recipeapp.data.source.local.entity.FavoriteRecipeEntity

@Database(
    entities = [FavoriteRecipeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun favoriteRecipeDao(): FavoriteRecipeDao
}