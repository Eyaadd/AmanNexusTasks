package com.example.newsapp.presentation.screen.post.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PostStatisticItem(
    value: Int,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,


) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        icon()

        Spacer(modifier = Modifier.width(2.dp))

        Text(
            text = value.toString(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
fun PostStatisticItemPreview() {
    PostStatisticItem(
        icon = {
            Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = "Views"
            )
        },
        value = 1,

        )
}