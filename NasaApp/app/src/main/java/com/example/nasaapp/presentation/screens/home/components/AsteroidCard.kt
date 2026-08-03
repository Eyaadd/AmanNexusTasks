package com.example.nasaapp.presentation.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.nasaapp.data.model.AsteroidUiModel

@Composable
 fun AsteroidCard(
    asteroid: AsteroidUiModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = asteroid.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            AsteroidInformationRow(
                label = "Close approach date",
                value = asteroid.closeApproachDate
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp)
            )

            AsteroidInformationRow(
                label = "Estimated diameter",
                value = asteroid.estimatedDiameterRange
            )

            AsteroidInformationRow(
                label = "Relative velocity",
                value = asteroid.relativeVelocity
            )

            AsteroidInformationRow(
                label = "Distance from Earth",
                value = asteroid.missDistance
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = asteroid.hazardStatus,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = if (asteroid.isPotentiallyHazardous) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.primary
                }
            )
        }
    }
}