package com.example.nasaapp.presentation.screens.home



import com.example.nasaapp.data.model.AsteroidUiModel

object HomeContract {

    data class HomeState(
        val isLoading: Boolean = false,
        val isRefreshing: Boolean = false,
        val asteroids: List<AsteroidUiModel> = emptyList(),
        val errorMessage: String? = null
    ) {
        val isEmpty: Boolean
            get() = asteroids.isEmpty() && !isLoading
    }

    sealed interface HomeIntent {

        data object RefreshAsteroids : HomeIntent

        data object Retry : HomeIntent

        data object DismissError : HomeIntent
    }
}