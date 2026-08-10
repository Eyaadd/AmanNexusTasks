package com.example.recipeapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.recipeapp.data.source.local.entity.FavoriteRecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRecipeDao {

    @Query("SELECT * FROM favorite_recipes")
    fun observeFavoriteRecipes(): Flow<List<FavoriteRecipeEntity>>

    @Query(
        """
        SELECT EXISTS(
            SELECT 1
            FROM favorite_recipes
            WHERE id = :recipeId
        )
        """
    )
    fun observeIsFavorite(
        recipeId: Int
    ): Flow<Boolean>

    @Upsert
    suspend fun upsertFavoriteRecipe(
        recipe: FavoriteRecipeEntity
    )

    @Query(
        "DELETE FROM favorite_recipes WHERE id = :recipeId"
    )
    suspend fun deleteFavoriteRecipeById(
        recipeId: Int
    )
}