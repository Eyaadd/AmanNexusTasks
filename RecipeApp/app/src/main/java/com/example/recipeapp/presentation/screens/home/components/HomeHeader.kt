package com.example.recipeapp.presentation.screens.home.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.recipeapp.presentation.theme.RecipeYellow
import com.example.recipeapp.presentation.theme.ScreenTextColor

@Composable
 fun HomeHeader(
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            append("Find ")

            withStyle(
                style = SpanStyle(
                    color = RecipeYellow
                )
            ) {
                append("best recipes")
            }

            append("\nfor cooking")
        },
        modifier = modifier,
        color = ScreenTextColor,
        fontSize = 30.sp,
        lineHeight = 34.sp,
        fontWeight = FontWeight.Bold
    )
}


@Preview
@Composable
fun HomeHeaderPreview(){
    HomeHeader()
}