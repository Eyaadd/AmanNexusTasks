package com.example.recipeapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.model.RecipeCategory
import com.example.recipeapp.data.model.RecipeSummaryUiModel
import com.example.recipeapp.data.repository.RecipeRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.UnknownHostException

class HomeViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeContract.HomeState())

    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<HomeContract.HomeEffect>(Channel.BUFFERED)

    val effect = _effect.receiveAsFlow()

    init {
        observeFavoriteRecipes()
        loadHome()
    }

    fun onIntent(intent: HomeContract.HomeIntent) {
        when (intent) {
            HomeContract.HomeIntent.Retry -> loadHome()

            is HomeContract.HomeIntent.OnCategorySelected -> selectCategory(intent.category)

            is HomeContract.HomeIntent.OnSearchQueryChanged -> _uiState.update {
                it.copy(searchQuery = intent.query)
            }

            is HomeContract.HomeIntent.OnRecipeClicked -> navigateToDetails(intent.recipe.id)

            is HomeContract.HomeIntent.OnFavoriteClicked -> toggleFavorite(intent.recipe)
            HomeContract.HomeIntent.OnSearchBarClicked -> navigateToSearch()
        }
    }

    private fun loadHome() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true, errorMessage = null
                )
            }

            try {
                val selectedCategory = _uiState.value.selectedCategory

                val trendingRecipesDeferred = async {
                    recipeRepository.getRecipes()
                }

                val popularDeferred = async {
                    recipeRepository.getPopularCategoryRecipes(
                        selectedCategory
                    )
                }

                val recentDeferred = async {
                    recipeRepository.getRecentRecipes()
                }
                val trendingRecipes = trendingRecipesDeferred.await()
                val popularRecipes = popularDeferred.await()
                val recentRecipes = recentDeferred.await()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        trendingRecipes = trendingRecipes,
                        popularCategoryRecipes = popularRecipes,
                        recentRecipes = recentRecipes
                    )
                }
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: ConnectException) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Couldn't connect to the server. Check your internet connection."
                    )
                }
            } catch (exception: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Unable to load recipes"
                    )
                }
            }
        }
    }

    private fun selectCategory(
        category: RecipeCategory
    ) {
        if (category == _uiState.value.selectedCategory) return

        _uiState.update {
            it.copy(
                selectedCategory = category, isCategoryLoading = true
            )
        }

        viewModelScope.launch {
            try {
                val recipes = recipeRepository.getPopularCategoryRecipes(category)

                _uiState.update {
                    it.copy(
                        isCategoryLoading = false, popularCategoryRecipes = recipes
                    )
                }
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                _uiState.update {
                    it.copy(isCategoryLoading = false)
                }

                _effect.send(
                    HomeContract.HomeEffect.ShowMessage(
                        exception.message ?: "Unable to load category"
                    )
                )
            }
        }
    }

    private fun navigateToDetails(recipeId: Int) {
        viewModelScope.launch {
            _effect.send(
                HomeContract.HomeEffect.NavigateToRecipeDetails(
                    recipeId
                )
            )
        }
    }

    private fun toggleFavorite(recipe: RecipeSummaryUiModel) {
        if (recipe.id in _uiState.value.favoriteRecipeIds) {
            removeRecipeFromFavorites(recipe.id)
        } else {
            addRecipeToFavorites(recipe)
        }
    }

    private fun observeFavoriteRecipes() {
        val favoriteRecipes = recipeRepository.observeFavoriteRecipes()
        viewModelScope.launch {
            favoriteRecipes.collect { favoriteRecipe ->
                _uiState.update { it ->
                    it.copy(
                        favoriteRecipeIds = favoriteRecipe.map {
                            it.id
                        }.toSet()
                    )
                }
            }
        }
    }

    private fun removeRecipeFromFavorites(recipeId: Int) {
        viewModelScope.launch {
            recipeRepository.removeRecipeFromFavorites(recipeId)
        }
    }

    private fun addRecipeToFavorites(
        recipe: RecipeSummaryUiModel
    ) {
        viewModelScope.launch {
            recipeRepository.addRecipeToFavorites(recipe)
        }
    }

    private fun navigateToSearch() {
        viewModelScope.launch {
            _effect.send(HomeContract.HomeEffect.NavigateToSearch)
        }
    }

}