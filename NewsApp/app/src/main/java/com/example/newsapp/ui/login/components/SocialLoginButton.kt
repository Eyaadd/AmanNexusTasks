package com.example.newsapp.ui.login.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.theme.authLoginButtonBorderColor
import com.example.newsapp.theme.authLoginButtonContentColor
import com.example.newsapp.theme.sourceSans


@Composable
fun SocialLoginButton(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = authLoginButtonBorderColor
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = authLoginButtonContentColor
        ),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            Icon(
                modifier = Modifier.align(Alignment.CenterStart),
                painter = painterResource(icon),
                contentDescription = null
            )

            Text(
                modifier = Modifier.align(Alignment.Center),
                text = text,
                fontFamily = sourceSans,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SocialLoginButtonPreview() {
    SocialLoginButton(
        text = "Continue with Google",
        icon = R.drawable.ic_google,
        onClick = {}
    )
}