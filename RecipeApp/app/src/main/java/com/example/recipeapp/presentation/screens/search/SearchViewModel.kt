package com.example.recipeapp.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.model.RecipeSummaryUiModel
import com.example.recipeapp.domain.usecase.AddRecipeToFavoriteUseCase
import com.example.recipeapp.domain.usecase.ObserveFavoriteRecipesUseCase
import com.example.recipeapp.domain.usecase.ObserveIsFavoriteUseCase
import com.example.recipeapp.domain.usecase.RemoveRecipeFromFavoritesUseCase
import com.example.recipeapp.domain.usecase.SearchForRecipeUseCase
import com.example.recipeapp.presentation.mapper.toUiMessage
import com.example.recipeapp.presentation.screens.home.HomeContract
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchForRecipeUseCase: SearchForRecipeUseCase,
    private val addRecipeToFavoriteUseCase: AddRecipeToFavoriteUseCase,
    private val observeFavoriteRecipesUseCase: ObserveFavoriteRecipesUseCase,
    private val removeRecipeFromFavoritesUseCase: RemoveRecipeFromFavoritesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchContract.SearchState())
    private val searchQueryFlow = MutableSharedFlow<String>(
        extraBufferCapacity = 1
    )
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<SearchContract.SearchEffect>(Channel.BUFFERED)

    val effect = _effect.receiveAsFlow()


    init {
        observeSearch()
        observeFavoriteRecipes()
    }

    fun onIntent(intent: SearchContract.SearchIntent) {
        when (intent) {
            is SearchContract.SearchIntent.OnFavoriteClicked -> toggleFavorite(intent.recipe)
            is SearchContract.SearchIntent.OnRecipeClicked -> navigateToDetailsScreen(intent.recipeId)
            is SearchContract.SearchIntent.OnSearchQueryChanged -> onSearchQueryChanged(intent.query)
        }
    }

    private fun onSearchQueryChanged(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }
        searchQueryFlow.tryEmit(query)

    }

    private fun navigateToDetailsScreen(recipeId: Int) {
        viewModelScope.launch {
            _effect.send(SearchContract.SearchEffect.NavigateToRecipeDetails(recipeId))
        }
    }


    private fun observeSearch() {
        viewModelScope.launch {
            searchQueryFlow.debounce(300).distinctUntilChanged().collectLatest { query ->
                if (query.isBlank()) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            searchResult = emptyList()
                        )
                    }
                    return@collectLatest
                }
                updateLoadingState()
                searchForRecipeUseCase(query).onSuccess { result ->
                    updateSuccessState(result)

                }.onFailure { throwable ->
                    updateErrorMessage(
                        throwable.message ?: "Error has occurred"
                    )
                }

            }

        }
    }

    private fun updateErrorMessage(message: String) {
        _uiState.update {
            it.copy(
                isLoading = false, errorMessage = message, searchResult = emptyList()
            )
        }
    }

    private fun updateLoadingState() {
        _uiState.update {
            it.copy(
                isLoading = true, errorMessage = null, searchResult = emptyList()
            )
        }
    }

    private fun updateSuccessState(result: List<RecipeSummaryUiModel>) {
        _uiState.update {
            it.copy(
                isLoading = false, searchResult = result, errorMessage = null

            )
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
                updateErrorMessage(
                    failure.message ?: "Error has occurred"
                )
            }
        }
    }
    private fun observeFavoriteRecipes() {
        viewModelScope.launch {
            observeFavoriteRecipesUseCase().catch { exception ->
                if (exception is CancellationException) {
                    throw exception
                }
                updateErrorMessage(
                    exception.toUiMessage()
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

    private fun addRecipeToFavorites(
        recipe: RecipeSummaryUiModel
    ) {
        viewModelScope.launch {
            val result = addRecipeToFavoriteUseCase(recipe)

            val failure = result.exceptionOrNull()

            if (failure != null) {
                updateErrorMessage(
                    failure.message ?: "Error has occurred"
                )
            }
        }
    }

}