package com.example.recipeapp.presentation.screens.details.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.R

@Composable
fun RecipeSourceRow(
    sourceName: String?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.chef_hat),
            contentDescription = null,
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = sourceName ?: "Unknown source",
            color = Color(0xFF222222),
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeSourceRowPreview() {
    RecipeSourceRow(
        sourceName = "Wade Warren"
    )
}