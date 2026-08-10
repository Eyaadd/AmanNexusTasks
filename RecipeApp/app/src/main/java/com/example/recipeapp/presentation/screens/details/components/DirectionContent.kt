package com.example.recipeapp.presentation.screens.details.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.domain.model.DirectionUiModel

@Composable
fun DirectionsContent(
    directions: List<DirectionUiModel>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        directions.forEach { direction ->
            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = direction.number.toString(),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = direction.step,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DirectionsContentPreview() {
    DirectionsContent(
        directions = listOf(
            DirectionUiModel(
                number = 1,
                step = "Prepare all ingredients."
            ),
            DirectionUiModel(
                number = 2,
                step = "Cook everything over medium heat."
            )
        )
    )
}
