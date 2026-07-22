package com.example.week2amantasksxml.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week2amantasksxml.R
import com.example.week2amantasksxml.theme.manropeSemiBold

@Composable
fun CustomOutlinedTextField(
    value: String, onValueChange: (String) -> Unit, placeHolder: String, label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFC4C4C4),
            unfocusedBorderColor = Color(0xFFC4C4C4),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        placeholder = { Text(placeHolder, color = Color(0xFFC4C4C4)) },
        label = {
            Text(label, fontFamily = manropeSemiBold, color = Color.Black)
        },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    )
}


@Composable
fun UserAgreementLayout(isChecked: Boolean, onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),

        ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(Color(0xFFF4F4F4))
                .clickable(
                    onClick = onClick
                ), contentAlignment = Alignment.Center
        ) {
            if (isChecked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.Blue,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Text(
            text = buildAnnotatedString {
                append("I’ve read and agreed to ")

                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF004643), fontWeight = FontWeight.Bold
                    )
                ) {
                    append("User Agreement")
                }

                append("\nand ")

                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF004643), fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Privacy Policy")
                }
            }, fontFamily = manropeSemiBold, fontSize = 14.sp
        )

    }
}

@Composable
fun LoginAuthLayout() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AuthIcon(id = R.drawable.ic_google, 48.dp)
        AuthIcon(id = R.drawable.ic_facebook, 48.dp)
    }
}


@Composable
fun AuthIcon(id: Int, size: Dp) {
    Image(
        painterResource(id = id),
        contentDescription = null,
        modifier = Modifier.size(size)
    )
}