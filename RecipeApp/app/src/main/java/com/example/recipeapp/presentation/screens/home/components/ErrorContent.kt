package com.example.recipeapp.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.recipeapp.presentation.theme.RecipeYellow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource
import com.example.recipeapp.R

@Composable
fun ErrorContent(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = message,
            color = Color.Red,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        TextButton(
            onClick = onRetry
        ) {
            Text(
                text = stringResource(R.string.retry),
                color = RecipeYellow,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ErrorContentPreview() {
    ErrorContent(
        message = stringResource(R.string.no_recipes_found),
        onRetry = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    )
}
