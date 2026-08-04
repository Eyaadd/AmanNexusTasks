package com.example.recipeapp.data.repository

import com.example.recipeapp.data.model.RecipeCategory
import com.example.recipeapp.data.model.RecipeDetailsUiModel
import com.example.recipeapp.data.model.RecipeSummaryUiModel
import com.example.recipeapp.data.source.local.dao.FavoriteRecipeDao
import com.example.recipeapp.data.source.remote.api.RecipeApi
import com.example.recipeapp.data.source.remote.mapper.toFavoriteRecipeEntity
import com.example.recipeapp.data.source.remote.mapper.toRecipeDetailsUiModel
import com.example.recipeapp.data.source.remote.mapper.toRecipeSummaryUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipeRepositoryImp(
    private val recipeApi: RecipeApi, private val favoriteRecipeDao: FavoriteRecipeDao
) : RecipeRepository {
    override suspend fun getRecipes(): List<RecipeSummaryUiModel> {
        return recipeApi.getRandomRecipes().recipes.map {
            it.toRecipeSummaryUiModel()
        }
    }

    override suspend fun getPopularCategoryRecipes(
        category: RecipeCategory
    ): List<RecipeSummaryUiModel> {
        return recipeApi.getPopularCategoryRecipes(
                query = category.query, type = category.type
            ).results.map { it.toRecipeSummaryUiModel() }


    }

    override suspend fun getRecentRecipes(): List<RecipeSummaryUiModel> {
        return recipeApi.getRecentRecipes().results.map { it.toRecipeSummaryUiModel() }
    }

    override fun observeFavoriteRecipes(): Flow<List<RecipeSummaryUiModel>> {
       return favoriteRecipeDao.observeFavoriteRecipes().map {
           favoriteRecipes ->
           favoriteRecipes.map {
               it.toRecipeSummaryUiModel()
           }
       }
    }

    override fun observeIsFavorite(recipeId: Int): Flow<Boolean> {
        return favoriteRecipeDao.observeIsFavorite(recipeId)
    }

    override suspend fun addRecipeToFavorites(recipe: RecipeSummaryUiModel) {
        favoriteRecipeDao.upsertFavoriteRecipe(recipe.toFavoriteRecipeEntity())
    }

    override suspend fun removeRecipeFromFavorites(recipeId: Int) {
        favoriteRecipeDao.deleteFavoriteRecipeById(recipeId)
    }

    override suspend fun getRecipeDetails(
        recipeId: Int
    ): RecipeDetailsUiModel {
        return recipeApi
            .getRecipeDetails(recipeId)
            .toRecipeDetailsUiModel()
    }


}


