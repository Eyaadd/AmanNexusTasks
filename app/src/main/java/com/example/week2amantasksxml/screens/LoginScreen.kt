package com.example.week2amantasksxml.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week2amantasksxml.R
import com.example.week2amantasksxml.components.CustomOutlinedTextField
import com.example.week2amantasksxml.components.LoginAuthLayout
import com.example.week2amantasksxml.components.UserAgreementLayout
import com.example.week2amantasksxml.theme.manropeSemiBold


@Composable
fun LoginScreenContent(onSignInClicked: () -> Unit = {},modifier: Modifier = Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isChecked by rememberSaveable() { mutableStateOf(false) }
    LoginScreenComposable(
        modifier = modifier,
        email = email,
        password = password,
        isChecked = isChecked,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onCheckChanged = {
            isChecked = !isChecked
        },
        onSignInClicked = onSignInClicked)
}


@Composable
fun LoginScreenComposable(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onSignInClicked: () -> Unit,
    isChecked: Boolean,
    onCheckChanged: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
    ) {


        Image(
            painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(103.dp)
                .height(81.dp)

        )
        Spacer(Modifier.size(32.dp))
        Text(
            text = "Sign in to your account", fontFamily = manropeSemiBold, fontSize = 20.sp
        )
        Spacer(Modifier.size(51.dp))
        CustomOutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            placeHolder = "Enter your email address",
            label = "Email Address"
        )
        Spacer(Modifier.size(43.dp))
        CustomOutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            placeHolder = "Enter your password",
            label = "Password",
        )
        Spacer(Modifier.size(16.dp))
        Text(
            text = "Forgot Password?",
            fontFamily = manropeSemiBold,
            color = Color(0xFF757575),
            fontSize = 14.sp,
            modifier = Modifier
                .clickable(
                    onClick = {

                    })
                .align(Alignment.End)
        )
        Spacer(Modifier.size(24.dp))
        UserAgreementLayout(
            isChecked = isChecked,
            onClick = onCheckChanged
        )
        Spacer(Modifier.size(40.dp))
        Button(
            onClick = onSignInClicked, modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonColors(
                containerColor = Color(0xFF004643),
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Gray
            )
        ) {
            Text("Sign in")
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "other way to sign in",
            fontFamily = manropeSemiBold,
            fontSize = 12.sp,
            color = Color(0xFF757575)
        )
        Spacer(Modifier.size(16.dp))
        LoginAuthLayout()
        Spacer(Modifier.size(40.dp))
        Text(
            text = buildAnnotatedString {
                append("Don't have an account? ")

                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF004643), fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Create Account")
                }
            }, fontFamily = manropeSemiBold, fontSize = 14.sp, color = Color(0xFF757575)
        )


    }
}


@Preview
@Composable
fun LoginScreenComposableContentPreview() {
    LoginScreenContent()
}


