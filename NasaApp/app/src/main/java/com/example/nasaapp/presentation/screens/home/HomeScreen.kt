package com.example.nasaapp.presentation.screens.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nasaapp.di.homeViewModelNamed
import com.example.nasaapp.presentation.screens.home.components.AsteroidsContent
import com.example.nasaapp.presentation.screens.home.components.EmptyContent
import com.example.nasaapp.presentation.screens.home.components.ErrorDialog
import com.example.nasaapp.presentation.screens.home.components.InitialLoadingContent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val viewModel : HomeViewModel = koinViewModel(qualifier = homeViewModelNamed)

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