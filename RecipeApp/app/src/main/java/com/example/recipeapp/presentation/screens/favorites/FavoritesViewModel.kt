package com.example.recipeapp.presentation.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.repository.RecipeRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(FavoritesContract.FavoritesState())

    val uiState = _uiState.asStateFlow()

    private val _effect =
        Channel<FavoritesContract.FavoritesEffect>(
            capacity = Channel.BUFFERED
        )

    val effect = _effect.receiveAsFlow()

    init {
        observeFavoriteRecipes()
    }

    fun onIntent(intent: FavoritesContract.FavoritesIntent) {
        when (intent) {
            is FavoritesContract.FavoritesIntent.OnRecipeClicked -> {
                navigateToRecipeDetails(intent.recipeId)
            }

            is FavoritesContract.FavoritesIntent.OnRemoveFavoriteClicked -> {
                removeRecipeFromFavorites(intent.recipeId)
            }

            is FavoritesContract.FavoritesIntent.OnTabSelected -> _uiState.update {
                it.copy(selectedTab = intent.tab)
            }
        }
    }

    private fun observeFavoriteRecipes() {
        viewModelScope.launch {
            try {
                recipeRepository
                    .observeFavoriteRecipes()
                    .collect { favoriteRecipes ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                favoriteRecipes = favoriteRecipes,
                                errorMessage = null
                            )
                        }
                    }
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = exception.message
                            ?: "Unable to load favorite recipes"
                    )
                }
            }
        }
    }

    private fun removeRecipeFromFavorites(
        recipeId: Int
    ) {
        viewModelScope.launch {
            try {
                recipeRepository.removeRecipeFromFavorites(
                    recipeId = recipeId
                )
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                _effect.send(
                    FavoritesContract.FavoritesEffect.ShowMessage(
                        message = exception.message
                            ?: "Unable to remove recipe"
                    )
                )
            }
        }
    }

    private fun navigateToRecipeDetails(
        recipeId: Int
    ) {
        viewModelScope.launch {
            _effect.send(
                FavoritesContract.FavoritesEffect.NavigateToRecipeDetails(
                    recipeId = recipeId
                )
            )
        }
    }
}