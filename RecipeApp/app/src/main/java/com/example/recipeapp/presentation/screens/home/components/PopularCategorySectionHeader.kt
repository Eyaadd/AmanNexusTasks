package com.example.recipeapp.presentation.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.data.model.RecipeCategory
import com.example.recipeapp.presentation.theme.ScreenTextColor
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PopularCategorySectionHeader(
    selectedCategory: RecipeCategory,
    onCategorySelected: (RecipeCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Popular category",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = ScreenTextColor
        )

        LazyRow(
            modifier = Modifier.padding(top = 12.dp),
            horizontalArrangement =
                Arrangement.spacedBy(28.dp)
        ) {
            items(
                items = RecipeCategory.entries,
                key = { it.name }
            ) { category ->
                Text(
                    text = category.displayName,
                    modifier = Modifier.clickable {
                        onCategorySelected(category)
                    },
                    color = if (category == selectedCategory) {
                        ScreenTextColor
                    } else {
                        Color.Gray
                    },
                    fontSize = 14.sp,
                    fontWeight =
                        if (category == selectedCategory) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Medium
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 420)
@Composable
fun PopularCategorySectionHeaderPreview() {
    PopularCategorySectionHeader(
        selectedCategory = RecipeCategory.VEGETABLES,
        onCategorySelected = {},
        modifier = Modifier.padding(16.dp)
    )
}