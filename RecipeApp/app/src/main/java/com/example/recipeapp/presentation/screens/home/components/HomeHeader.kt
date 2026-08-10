package com.example.recipeapp.presentation.screens.home.components

import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import com.example.recipeapp.R
import com.example.recipeapp.presentation.theme.RecipeYellow

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier
) {
    val prefix = stringResource(R.string.home_header_prefix)
    val highlight = stringResource(R.string.home_header_highlight)
    val suffix = stringResource(R.string.home_header_suffix)
    Text(
        text = buildAnnotatedString {
            append(prefix)

            withStyle(
                style = SpanStyle(
                    color = RecipeYellow
                )
            ) {
                append(highlight)
            }

            append(suffix)
        },
        modifier = modifier,
        color = MaterialTheme.colorScheme.onBackground,
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
