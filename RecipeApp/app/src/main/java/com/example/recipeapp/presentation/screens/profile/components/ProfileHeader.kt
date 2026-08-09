package com.example.recipeapp.presentation.screens.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R
import com.example.recipeapp.presentation.theme.ProfileSecondaryText
import com.example.recipeapp.presentation.theme.RecipeYellow
import com.example.recipeapp.presentation.theme.ScreenTextColor

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(top = 22.dp, bottom = 28.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_profile_placeholder),
            contentDescription = "Profile photo",
            modifier = Modifier
                .size(64.dp)
                .border(1.5.dp, RecipeYellow, CircleShape)
                .padding(2.dp)
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Text(
                text = "Albert Hasan",
                color = ScreenTextColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "@bertt_h.san", color = ProfileSecondaryText, fontSize = 13.sp)
        }
    }
}
