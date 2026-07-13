package com.example.week2amantasksxml.viewmodels.login

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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week2amantasksxml.R
import com.example.week2amantasksxml.components.CustomOutlinedTextField
import com.example.week2amantasksxml.components.LoginAuthLayout
import com.example.week2amantasksxml.components.UserAgreementLayout
import com.example.week2amantasksxml.theme.darkGray
import com.example.week2amantasksxml.theme.darkGreen
import com.example.week2amantasksxml.viewmodels.LoginScreenViewModel
import com.example.week2amantasksxml.viewmodels.LoginUiState
import com.example.week2amantasksxml.theme.manropeSemiBold


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginScreenViewModel = viewModel(),
    onSignInClicked: () -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isLoggedIn) {
        if (uiState.isLoggedIn) {
            onSignInClicked()
            viewModel.resetLoginState()
        }
    }
    LoginScreenContent(
        modifier = modifier,
        uiState = uiState,
        onEmailChange = { viewModel.onEmailChange(it) },
        onPasswordChange = { viewModel.onPasswordChange(it) },
        onCheckChanged = {
            viewModel.onCheckChanged()
        },
        onSignInClicked = { viewModel.login() })
}


@Composable
fun LoginScreenContent(
    uiState: LoginUiState,
    modifier: Modifier = Modifier,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClicked: () -> Unit,
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
            painter = painterResource(R.drawable.ic_logo),
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
            value = uiState.email,
            onValueChange = onEmailChange,
            placeHolder = "Enter your email address",
            label = "Email Address"
        )

        Spacer(Modifier.size(43.dp))

        CustomOutlinedTextField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            placeHolder = "Enter your password",
            label = "Password"
        )

        Spacer(Modifier.size(16.dp))

        Text(
            text = "Forgot Password?",
            fontFamily = manropeSemiBold,
            color = darkGray,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { })

        Spacer(Modifier.size(24.dp))

        UserAgreementLayout(
            isChecked = uiState.isChecked, onClick = onCheckChanged
        )

        Spacer(Modifier.size(16.dp))

        if (uiState.errorMessage != null) {
            Text(
                text = uiState.errorMessage,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.size(16.dp))
        }

        Button(
            onClick = onSignInClicked,
            enabled = !uiState.loading,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = darkGreen,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            )
        ) {

            if (uiState.loading) {

                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp), strokeWidth = 2.dp, color = Color.White
                )

            } else {

                Text("Sign in")

            }
        }

        Spacer(Modifier.size(16.dp))

        Text(
            text = "other way to sign in",
            fontFamily = manropeSemiBold,
            fontSize = 12.sp,
            color = darkGray
        )

        Spacer(Modifier.size(16.dp))

        LoginAuthLayout()

        Spacer(Modifier.size(40.dp))

        Text(
            text = buildAnnotatedString {
                append("Don't have an account? ")

                withStyle(
                    SpanStyle(
                        color = darkGreen, fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Create Account")
                }
            }, fontFamily = manropeSemiBold, fontSize = 14.sp, color = darkGray
        )
    }
}


@Preview
@Composable
fun LoginScreenComposablePreview() {
    LoginScreen()
}


