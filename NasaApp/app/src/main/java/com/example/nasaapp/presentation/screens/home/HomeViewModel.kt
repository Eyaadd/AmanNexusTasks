package com.example.nasaapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaapp.data.repository.AsteroidRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: AsteroidRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeContract.HomeState()
    )

    val uiState = _uiState.asStateFlow()

    init {
        observeAsteroids()
        refreshAsteroids()
    }

    fun onIntent(intent: HomeContract.HomeIntent) {
        when (intent) {
            HomeContract.HomeIntent.RefreshAsteroids -> {
                refreshAsteroids()
            }

            HomeContract.HomeIntent.Retry -> {
                refreshAsteroids()
            }

            HomeContract.HomeIntent.DismissError -> {
                dismissError()
            }
        }
    }

    private fun observeAsteroids() {
        viewModelScope.launch {
            repository.observeAsteroids()
                .onStart {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = currentState.asteroids.isEmpty()
                        )
                    }
                }
                .catch { throwable ->
                    if (throwable is CancellationException) {
                        throw throwable
                    }

                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            errorMessage = throwable.message
                                ?: "Failed to load cached asteroids."
                        )
                    }
                }
                .collect { asteroids ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            asteroids = asteroids
                        )
                    }
                }
        }
    }

    private fun refreshAsteroids() {
        viewModelScope.launch {
            val hasCachedAsteroids = _uiState.value.asteroids.isNotEmpty()

            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = !hasCachedAsteroids,
                    isRefreshing = hasCachedAsteroids,
                    errorMessage = null
                )
            }

            try {
                repository.refreshAsteroids()
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        errorMessage = exception.message
                            ?: "Failed to refresh asteroids."
                    )
                }
            } finally {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        isRefreshing = false
                    )
                }
            }
        }
    }

    private fun dismissError() {
        _uiState.update { currentState ->
            currentState.copy(
                errorMessage = null
            )
        }
    }
}