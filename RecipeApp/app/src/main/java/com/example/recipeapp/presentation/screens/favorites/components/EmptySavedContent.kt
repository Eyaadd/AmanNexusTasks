package com.example.recipeapp.presentation.screens.favorites.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.recipeapp.presentation.theme.ScreenTextColor

@Composable
 fun EmptySavedContent(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = ScreenTextColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}