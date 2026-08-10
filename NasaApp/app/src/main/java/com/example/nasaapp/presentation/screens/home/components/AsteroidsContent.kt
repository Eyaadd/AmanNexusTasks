package com.example.nasaapp.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nasaapp.data.model.AsteroidUiModel

@Composable
 fun AsteroidsContent(
    asteroids: List<AsteroidUiModel>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if (isRefreshing) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }

        HomeHeader(
            onRefresh = onRefresh
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = asteroids,
                key = { asteroid ->
                    asteroid.id
                }
            ) { asteroid ->
                AsteroidCard(
                    asteroid = asteroid
                )
            }
        }
    }
}