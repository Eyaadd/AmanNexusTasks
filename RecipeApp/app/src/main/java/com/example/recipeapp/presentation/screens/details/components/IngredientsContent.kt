package com.example.recipeapp.presentation.screens.details.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IngredientsContent(
    servings: Int?,
    ingredients: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Text(
            text = servings
                ?.let { "Serves $it people" }
                ?: "Servings unavailable",
            color = Color(0xFF222222),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        ingredients.forEach { ingredient ->
            Text(
                text = ingredient,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IngredientsContentPreview() {
    IngredientsContent(
        servings = 4,
        ingredients = listOf(
            "300 g chicken feet",
            "2 pcs beef sausage",
            "5 pcs beef meatballs",
            "1 egg beaten"
        )
    )
}