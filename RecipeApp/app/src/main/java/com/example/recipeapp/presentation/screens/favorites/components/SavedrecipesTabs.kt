package com.example.recipeapp.presentation.screens.favorites.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.domain.model.SavedTab
import com.example.recipeapp.presentation.screens.favorites.FavoritesContract
import com.example.recipeapp.presentation.theme.RecipeYellow

@Composable
fun SavedRecipesTabs(
    selectedTab: SavedTab,
    onTabSelected: (SavedTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        SavedTabItem(
            title = "Video",
            isSelected =
                selectedTab == SavedTab.VIDEO,
            onClick = {
                onTabSelected(SavedTab.VIDEO)
            },
            modifier = Modifier.weight(1f)
        )

        SavedTabItem(
            title = "Recipes",
            isSelected =
                selectedTab == SavedTab.RECIPES,
            onClick = {
                onTabSelected(SavedTab.RECIPES)
            },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun SavedTabItem(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .height(50.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        color = if (isSelected) {
            RecipeYellow
        } else {
            Color.Transparent
        }
    ) {
        androidx.compose.foundation.layout.Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = if (isSelected) {
                    Color.White
                } else {
                    RecipeYellow
                },
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 420
)
@Composable
private fun SavedRecipesTabsPreview() {
    SavedRecipesTabs(
        selectedTab = SavedTab.RECIPES,
        onTabSelected = {}
    )
}