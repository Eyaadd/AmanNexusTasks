package com.example.recipeapp.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun TrendingHeaderShimmer(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(170.dp)
            .height(28.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray)
            .shimmer()
    )
}


@Preview(showBackground = true)
@Composable
fun TrendingHeaderShimmerPreview(){
    TrendingHeaderShimmer()
}