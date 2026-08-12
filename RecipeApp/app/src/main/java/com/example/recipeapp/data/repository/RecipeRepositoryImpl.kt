package com.example.recipeapp.data.repository

import com.example.recipeapp.data.source.local.dao.FavoriteRecipeDao
import com.example.recipeapp.data.source.remote.api.RecipeApi
import com.example.recipeapp.data.source.remote.mapper.toFavoriteRecipeEntity
import com.example.recipeapp.data.source.remote.mapper.toRecipeDetailsUiModel
import com.example.recipeapp.data.source.remote.mapper.toRecipeSummaryUiModel
import com.example.recipeapp.domain.model.RecipeCategory
import com.example.recipeapp.domain.model.RecipeDetailsUiModel
import com.example.recipeapp.domain.model.RecipeSummaryUiModel
import com.example.recipeapp.domain.repository.RecipeRepository
import com.example.recipeapp.utils.safeCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipeRepositoryImpl(
    private val recipeApi: RecipeApi, private val favoriteRecipeDao: FavoriteRecipeDao
) : RecipeRepository {

    override suspend fun getRecipes(): Result<List<RecipeSummaryUiModel>> {
        return safeCall {
            recipeApi.getRandomRecipes().recipes.map { recipeDto ->
                    recipeDto.toRecipeSummaryUiModel()
                }
        }
}
    override suspend fun getPopularCategoryRecipes(
        category: RecipeCategory
    ): Result<List<RecipeSummaryUiModel>> {
        return safeCall {
            recipeApi.getPopularCategoryRecipes(
                    query = category.query, type = category.type
                ).results.map { recipeDto ->
                    recipeDto.toRecipeSummaryUiModel()
                }
        }
    }

    override suspend fun getRecentRecipes(): Result<List<RecipeSummaryUiModel>> {
        return safeCall {
            recipeApi.getRecentRecipes().results.map { recipeDto ->
                    recipeDto.toRecipeSummaryUiModel()
                }
        }
    }

    override fun observeFavoriteRecipes(): Flow<List<RecipeSummaryUiModel>> {
        return favoriteRecipeDao.observeFavoriteRecipes().map { favoriteRecipes ->
                favoriteRecipes.map { favoriteRecipe ->
                    favoriteRecipe.toRecipeSummaryUiModel()
                }
            }
    }

    override fun observeIsFavorite(
        recipeId: Int
    ): Flow<Boolean> {
        return favoriteRecipeDao.observeIsFavorite(recipeId)
    }

    override suspend fun addRecipeToFavorites(
        recipe: RecipeSummaryUiModel
    ): Result<Unit> {
        return safeCall {
            favoriteRecipeDao.upsertFavoriteRecipe(
                recipe.toFavoriteRecipeEntity()
            )
        }
    }

    override suspend fun removeRecipeFromFavorites(
        recipeId: Int
    ): Result<Unit> {
        return safeCall {
            favoriteRecipeDao.deleteFavoriteRecipeById(recipeId)
        }
    }

    override suspend fun getRecipeDetails(
        recipeId: Int
    ): Result<RecipeDetailsUiModel> {
        return safeCall {
            recipeApi.getRecipeDetails(recipeId).toRecipeDetailsUiModel()
        }
    }

    override suspend fun searchForRecipe(
        query: String?
    ): Result<List<RecipeSummaryUiModel>> {
        return safeCall {
            recipeApi.searchForRecipe(query).results.map { recipeDto ->
                recipeDto.toRecipeSummaryUiModel()
            }
        }
    }
}
