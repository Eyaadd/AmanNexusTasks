package com.example.recipeapp.presentation.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.domain.model.RecipeCategory
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource
import com.example.recipeapp.R

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
            text = stringResource(R.string.popular_category),
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
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
                    text = category.localizedName(),
                    modifier = Modifier.clickable {
                        onCategorySelected(category)
                    },
                    color = if (category == selectedCategory) {
                        MaterialTheme.colorScheme.onBackground
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

@Composable
private fun RecipeCategory.localizedName(): String = stringResource(
    when (this) {
        RecipeCategory.VEGETABLES -> R.string.category_vegetables
        RecipeCategory.MEAT -> R.string.category_meat
        RecipeCategory.SALAD -> R.string.category_salad
        RecipeCategory.NOODLE -> R.string.category_noodle
        RecipeCategory.BREAKFAST -> R.string.category_breakfast
    }
)

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 420)
@Composable
fun PopularCategorySectionHeaderPreview() {
    PopularCategorySectionHeader(
        selectedCategory = RecipeCategory.VEGETABLES,
        onCategorySelected = {},
        modifier = Modifier.padding(16.dp)
    )
}
