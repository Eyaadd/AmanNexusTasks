package com.example.newsapp.ui.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.newsapp.theme.darkGray
import com.example.newsapp.theme.sourceSans


@Composable
fun BottomAuthText(
    modifier: Modifier = Modifier, onSignUpClick: () -> Unit
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Don't have an account? ", color = darkGray, fontFamily = sourceSans
        )

        Text(
            text = "Sign Up",
            color = Color.Black,
            fontFamily = sourceSans,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.clickable {
                onSignUpClick()
            })
    }


}