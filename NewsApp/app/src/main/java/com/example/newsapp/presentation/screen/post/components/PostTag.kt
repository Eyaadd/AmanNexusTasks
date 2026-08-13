package com.example.newsapp.presentation.screen.post.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PostTags(
    tags: List<String>,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tags.forEach { tag ->
            AssistChip(
                onClick = {},
                label = {
                    Text(text = "#$tag")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostTagsPreview(){
    PostTags(
        tags = listOf(
            "Eyad",
            "Mahmoud",
            "Sara",
            "Assem",
            "Aziza"
        )
    )
}