package com.example.recipeapp.presentation.screens.details


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.model.DetailsTab
import com.example.recipeapp.data.repository.RecipeRepository
import com.example.recipeapp.data.source.remote.mapper.toRecipeSummaryUiModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val recipeId: Int,
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(DetailsContract.DetailsState())

    val uiState = _uiState.asStateFlow()

    private val _effect =
        Channel<DetailsContract.DetailsEffect>(
            capacity = Channel.BUFFERED
        )

    val effect = _effect.receiveAsFlow()

    init {
        loadRecipeDetails()
        observeFavoriteState()
    }

    fun onIntent(intent: DetailsContract.DetailsIntent) {
        when (intent) {
            DetailsContract.DetailsIntent.Retry -> {
                loadRecipeDetails()
            }

            DetailsContract.DetailsIntent.OnBackClicked -> {
                sendEffect(
                    DetailsContract.DetailsEffect.NavigateBack
                )
            }


            DetailsContract.DetailsIntent.OnFavoriteClicked -> {
                toggleFavorite()
            }

            is DetailsContract.DetailsIntent.OnTabSelected -> {
                selectTab(intent.tab)
            }
        }
    }

    private fun loadRecipeDetails() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            try {
                val recipe =
                    recipeRepository.getRecipeDetails(recipeId)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        recipe = recipe,
                        errorMessage = null
                    )
                }
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = exception.message
                            ?: "Unable to load recipe details"
                    )
                }
            }
        }
    }

    private fun observeFavoriteState() {
        viewModelScope.launch {
            recipeRepository
                .observeIsFavorite(recipeId)
                .collect { isFavorite ->
                    _uiState.update {
                        it.copy(isFavorite = isFavorite)
                    }
                }
        }
    }

    private fun selectTab(tab: DetailsTab) {
        _uiState.update {
            it.copy(selectedTab = tab)
        }
    }

    private fun toggleFavorite() {
        val recipe = _uiState.value.recipe ?: return

        viewModelScope.launch {
            try {
                if (_uiState.value.isFavorite) {
                    recipeRepository.removeRecipeFromFavorites(
                        recipeId = recipe.id
                    )
                } else {
                    recipeRepository.addRecipeToFavorites(
                        recipe = recipe.toRecipeSummaryUiModel()
                    )
                }
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                sendEffect(
                    DetailsContract.DetailsEffect.ShowMessage(
                        message = exception.message
                            ?: "Unable to update favorite"
                    )
                )
            }
        }
    }


    private fun sendEffect(
        effect: DetailsContract.DetailsEffect
    ) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }
}