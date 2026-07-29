package com.example.nasaapp.presentation.screens.home


import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nasaapp.BuildConfig
import com.example.nasaapp.presentation.screens.home.components.AsteroidsContent
import com.example.nasaapp.presentation.screens.home.components.EmptyContent
import com.example.nasaapp.presentation.screens.home.components.ErrorDialog
import com.example.nasaapp.presentation.screens.home.components.InitialLoadingContent

@Composable
fun HomeScreen(
    homeViewModelFactory: HomeViewModelFactory,
    modifier: Modifier = Modifier
) {
    val viewModel: HomeViewModel = viewModel(
        factory = homeViewModelFactory
    )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        uiState = uiState,
        onIntent = viewModel::onIntent,
        modifier = modifier
    )
}

@Composable
fun HomeScreenContent(
    uiState: HomeContract.HomeState,
    onIntent: (HomeContract.HomeIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Log.d("NASA_KEY", BuildConfig.NASA_API_KEY)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                uiState.isLoading -> {
                    InitialLoadingContent(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                uiState.asteroids.isEmpty() -> {
                    EmptyContent(
                        onRetry = {
                            onIntent(HomeContract.HomeIntent.Retry)
                        },
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    AsteroidsContent(
                        asteroids = uiState.asteroids,
                        isRefreshing = uiState.isRefreshing,
                        onRefresh = {
                            onIntent(
                                HomeContract.HomeIntent.RefreshAsteroids
                            )
                        }
                    )
                }
            }
        }
    }

    uiState.errorMessage?.let { errorMessage ->
        ErrorDialog(
            errorMessage = errorMessage,
            onRetry = {
                onIntent(HomeContract.HomeIntent.Retry)
            },
            onDismiss = {
                onIntent(HomeContract.HomeIntent.DismissError)
            }
        )
    }
}