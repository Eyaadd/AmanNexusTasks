package com.example.recipeapp.presentation.screens.details.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.data.model.DetailsTab

@Composable
fun DetailsTabs(
    selectedTab: DetailsTab,
    onTabSelected: (DetailsTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        DetailsTabItem(
            title = "Ingredient",
            isSelected = selectedTab == DetailsTab.INGREDIENTS,
            onClick = {
                onTabSelected(DetailsTab.INGREDIENTS)
            },
            modifier = Modifier.weight(1f)
        )

        DetailsTabItem(
            title = "Direction",
            isSelected = selectedTab == DetailsTab.DIRECTIONS,
            onClick = {
                onTabSelected(DetailsTab.DIRECTIONS)
            },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun DetailsTabItem(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(50.dp)
            .clip(RoundedCornerShape(14.dp))
            .then(
                if (isSelected) {
                    Modifier
                        .clickable(onClick = onClick)
                        .clip(RoundedCornerShape(14.dp))
                } else {
                    Modifier.clickable(onClick = onClick)
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.material3.Surface(
            modifier = Modifier.matchParentSize(),
            shape = RoundedCornerShape(14.dp),
            color = if (isSelected) {
                Color(0xFF8B5600)
            } else {
                Color.Transparent
            }
        ) {}

        Text(
            text = title,
            color = if (isSelected) {
                Color.White
            } else {
                Color(0xFF8B5600)
            },
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsTabsPreview() {
    DetailsTabs(
        selectedTab = DetailsTab.INGREDIENTS,
        onTabSelected = {}
    )
}