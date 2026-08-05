package com.example.recipeapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.model.RecipeCategory
import com.example.recipeapp.domain.model.RecipeSummaryUiModel
import com.example.recipeapp.domain.usecase.AddRecipeToFavoriteUseCase
import com.example.recipeapp.domain.usecase.GetPopularCategoryRecipesUseCase
import com.example.recipeapp.domain.usecase.GetRecentRecipesUseCase
import com.example.recipeapp.domain.usecase.GetRecipesUseCase
import com.example.recipeapp.domain.usecase.ObserveFavoriteRecipesUseCase
import com.example.recipeapp.domain.usecase.RemoveRecipeFromFavoritesUseCase
import com.example.recipeapp.presentation.mapper.toUiMessage
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val getPopularCategoryRecipesUseCase: GetPopularCategoryRecipesUseCase,
    private val getRecentRecipesUseCase: GetRecentRecipesUseCase,
    private val observeFavoriteRecipesUseCase: ObserveFavoriteRecipesUseCase,
    private val removeRecipeFromFavoritesUseCase: RemoveRecipeFromFavoritesUseCase,
    private val addRecipeToFavoriteUseCase: AddRecipeToFavoriteUseCase
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
            HomeContract.HomeIntent.Retry -> {
                loadHome()
            }

            is HomeContract.HomeIntent.OnCategorySelected -> {
                selectCategory(intent.category)
            }

            is HomeContract.HomeIntent.OnSearchQueryChanged -> {
                updateSearchQuery(intent.query)
            }

            is HomeContract.HomeIntent.OnRecipeClicked -> {
                navigateToDetails(intent.recipe.id)
            }

            is HomeContract.HomeIntent.OnFavoriteClicked -> {
                toggleFavorite(intent.recipe)
            }

            HomeContract.HomeIntent.OnSearchBarClicked -> {
                navigateToSearch()
            }
        }
    }

    private fun loadHome() {
        viewModelScope.launch {
            updateLoadingState()

            try {
                val selectedCategory = _uiState.value.selectedCategory

                val trendingDeferred = async {
                    getRecipesUseCase()
                }

                val popularDeferred = async {
                    getPopularCategoryRecipesUseCase(selectedCategory)
                }

                val recentDeferred = async {
                    getRecentRecipesUseCase()
                }

                val trendingResult = trendingDeferred.await()
                val popularResult = popularDeferred.await()
                val recentResult = recentDeferred.await()

                val failure = listOf(
                    trendingResult, popularResult, recentResult
                ).firstNotNullOfOrNull { result ->
                    result.exceptionOrNull()
                }

                if (failure != null) {
                    updateErrorState(
                        message = failure.toUiMessage()
                    )
                    return@launch
                }

                updateSuccessState(
                    trendingRecipes = trendingResult.getOrThrow(),
                    popularRecipes = popularResult.getOrThrow(),
                    recentRecipes = recentResult.getOrThrow()
                )
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                updateErrorState(
                    message = exception.toUiMessage()
                )
            }
        }
    }

    private fun selectCategory(
        category: RecipeCategory
    ) {
        if (category == _uiState.value.selectedCategory) {
            return
        }

        _uiState.update { currentState ->
            currentState.copy(
                selectedCategory = category
            )
        }
        loadHome()
    }

    private fun updateSearchQuery(
        query: String
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = query
            )
        }
    }

    private fun observeFavoriteRecipes() {
        viewModelScope.launch {
            observeFavoriteRecipesUseCase().catch { exception ->
                    if (exception is CancellationException) {
                        throw exception
                    }

                    _effect.send(
                        HomeContract.HomeEffect.ShowMessage(
                            message = exception.toUiMessage()
                        )
                    )
                }.collect { favoriteRecipeIds ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            favoriteRecipeIds = favoriteRecipeIds
                        )
                    }
                }
        }
    }

    private fun toggleFavorite(
        recipe: RecipeSummaryUiModel
    ) {
        val isFavorite = recipe.id in _uiState.value.favoriteRecipeIds

        if (isFavorite) {
            removeRecipeFromFavorites(recipe.id)
        } else {
            addRecipeToFavorites(recipe)
        }
    }

    private fun removeRecipeFromFavorites(
        recipeId: Int
    ) {
        viewModelScope.launch {
            val result = removeRecipeFromFavoritesUseCase(recipeId)

            val failure = result.exceptionOrNull()

            if (failure != null) {
                _effect.send(
                    HomeContract.HomeEffect.ShowMessage(
                        message = failure.toUiMessage()
                    )
                )
            }
        }
    }

    private fun addRecipeToFavorites(
        recipe: RecipeSummaryUiModel
    ) {
        viewModelScope.launch {
            val result = addRecipeToFavoriteUseCase(recipe)

            val failure = result.exceptionOrNull()

            if (failure != null) {
                _effect.send(
                    HomeContract.HomeEffect.ShowMessage(
                        message = failure.toUiMessage()
                    )
                )
            }
        }
    }

    private fun navigateToDetails(
        recipeId: Int
    ) {
        viewModelScope.launch {
            _effect.send(
                HomeContract.HomeEffect.NavigateToRecipeDetails(
                    recipeId = recipeId
                )
            )
        }
    }

    private fun navigateToSearch() {
        viewModelScope.launch {
            _effect.send(
                HomeContract.HomeEffect.NavigateToSearch
            )
        }
    }

    private fun updateLoadingState() {
        _uiState.update { currentState ->
            currentState.copy(
                isLoading = true, errorMessage = null
            )
        }
    }

    private fun updateSuccessState(
        trendingRecipes: List<RecipeSummaryUiModel>,
        popularRecipes: List<RecipeSummaryUiModel>,
        recentRecipes: List<RecipeSummaryUiModel>
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                isLoading = false,
                errorMessage = null,
                trendingRecipes = trendingRecipes,
                popularCategoryRecipes = popularRecipes,
                recentRecipes = recentRecipes
            )
        }
    }

    private fun updateErrorState(
        message: String
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                isLoading = false,
                errorMessage = message,
                trendingRecipes = emptyList(),
                popularCategoryRecipes = emptyList(),
                recentRecipes = emptyList()
            )
        }
    }
}