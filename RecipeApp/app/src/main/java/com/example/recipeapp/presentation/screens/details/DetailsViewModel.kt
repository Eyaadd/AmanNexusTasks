package com.example.recipeapp.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.source.remote.mapper.toRecipeSummaryUiModel
import com.example.recipeapp.domain.model.DetailsTab
import com.example.recipeapp.domain.usecase.AddRecipeToFavoriteUseCase
import com.example.recipeapp.domain.usecase.GetRecipeDetailsUseCase
import com.example.recipeapp.domain.usecase.ObserveIsFavoriteUseCase
import com.example.recipeapp.domain.usecase.RemoveRecipeFromFavoritesUseCase
import com.example.recipeapp.presentation.mapper.toUiMessage
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val recipeId: Int,
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val observeIsFavoriteUseCase: ObserveIsFavoriteUseCase,
    private val addRecipeToFavoriteUseCase: AddRecipeToFavoriteUseCase,
    private val removeRecipeFromFavoritesUseCase: RemoveRecipeFromFavoritesUseCase
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
            updateLoadingState()

            try {
                val result = getRecipeDetailsUseCase(recipeId)

                val failure = result.exceptionOrNull()

                if (failure != null) {
                    updateErrorState(
                        message = failure.toUiMessage()
                    )
                    return@launch
                }

                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        recipe = result.getOrThrow(),
                        errorMessage = null
                    )
                }
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                updateErrorState(
                    message = exception.toUiMessage()
                )
            }
        }
    }

    private fun observeFavoriteState() {
        viewModelScope.launch {
            observeIsFavoriteUseCase(recipeId)
                .catch { exception ->
                    if (exception is CancellationException) {
                        throw exception
                    }

                    sendEffect(
                        DetailsContract.DetailsEffect.ShowMessage(
                            message = exception.toUiMessage()
                        )
                    )
                }
                .collect { isFavorite ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            isFavorite = isFavorite
                        )
                    }
                }
        }
    }

    private fun toggleFavorite() {
        val recipe = _uiState.value.recipe ?: return

        viewModelScope.launch {
            val result = if (_uiState.value.isFavorite) {
                removeRecipeFromFavoritesUseCase(
                    recipeId = recipe.id
                )
            } else {
                addRecipeToFavoriteUseCase(
                    recipe = recipe.toRecipeSummaryUiModel()
                )
            }

            val failure = result.exceptionOrNull()

            if (failure != null) {
                _effect.send(
                    DetailsContract.DetailsEffect.ShowMessage(
                        message = failure.toUiMessage()
                    )
                )
            }
        }
    }

    private fun selectTab(
        tab: DetailsTab
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedTab = tab
            )
        }
    }

    private fun updateLoadingState() {
        _uiState.update { currentState ->
            currentState.copy(
                isLoading = true,
                errorMessage = null
            )
        }
    }

    private fun updateErrorState(
        message: String
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                isLoading = false,
                errorMessage = message
            )
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