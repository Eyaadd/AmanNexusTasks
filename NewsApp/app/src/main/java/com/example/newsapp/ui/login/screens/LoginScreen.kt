package com.example.newsapp.ui.login.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.ui.login.components.CustomOutlinedTextField
import com.example.newsapp.ui.login.components.SocialLoginButton
import com.example.newsapp.ui.login.viewmodels.LoginUiState
import com.example.newsapp.ui.login.viewmodels.LoginViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.newsapp.R
import com.example.newsapp.theme.activeButtonColor
import com.example.newsapp.theme.darkGray
import com.example.newsapp.theme.disabledButtonColor
import com.example.newsapp.theme.roboto
import com.example.newsapp.theme.sourceSans
import com.example.newsapp.ui.login.components.BottomAuthText

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit = {},
    onForgotPassword: () -> Unit = {},
    onSignUp: () -> Unit = {},
    onGoogleClick: () -> Unit = {},
    onFacebookClick: () -> Unit = {}
) {


    val formState by viewModel.formState.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember {
        SnackbarHostState()
    }
    LaunchedEffect(uiState) {

        when (val state = uiState) {

            LoginUiState.Success -> {
                onLoginSuccess()
                viewModel.resetUiState()
            }

            is LoginUiState.Error -> {
                snackbarHostState.showSnackbar(state.message)
                viewModel.resetUiState()
            }

            else -> Unit
        }

    }
    Scaffold(

        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .background(Color.White)
                .fillMaxSize()
                .padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(56.dp))

            Text(
                text = "Sign In",
                fontFamily = roboto,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Stay informed effortlessly. Sign in and explore a world of news.",
                fontFamily = sourceSans,
                color = darkGray
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomOutlinedTextField(
                value = formState.email,
                onValueChange = viewModel::onEmailChanged,
                placeHolder = "Email",
                leadingIcon = R.drawable.ic_sms,
                isError = formState.emailError != null,
                errorMessage = formState.emailError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
            CustomOutlinedTextField(
                value = formState.password,
                onValueChange = viewModel::onPasswordChanged,
                placeHolder = "Password",
                leadingIcon = R.drawable.ic_lock,
                trailingIcon = if (formState.isPasswordVisible) {
                    R.drawable.eye_off
                } else {
                    R.drawable.ic_eye
                },
                onTrailingIconClick = viewModel::togglePasswordVisibility,
                visualTransformation =
                    if (formState.isPasswordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable(onClick = onForgotPassword),
                text = "Forgot Password?",
                fontFamily = sourceSans,
                color = darkGray
            )

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                enabled = uiState !is LoginUiState.Loading,
                onClick = viewModel::login,
                colors = ButtonDefaults.buttonColors(
                    containerColor = activeButtonColor,
                    disabledContainerColor = disabledButtonColor,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {

                if (uiState is LoginUiState.Loading) {

                    CircularProgressIndicator(
                        modifier = Modifier.size(22.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )

                } else {

                    Text(
                        text = "Sign In",
                        fontFamily = sourceSans,
                        fontWeight = FontWeight.SemiBold
                    )

                }
            }

            Spacer(modifier = Modifier.height(36.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                HorizontalDivider(
                    modifier = Modifier.weight(1f)
                )

                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = "OR",
                    fontFamily = sourceSans,
                    color = darkGray
                )

                HorizontalDivider(
                    modifier = Modifier.weight(1f)
                )

            }

            Spacer(modifier = Modifier.height(36.dp))
            SocialLoginButton(
                text = "Continue with Google",
                icon = R.drawable.ic_google,
                onClick = onGoogleClick
            )
            Spacer(modifier = Modifier.height(16.dp))
            SocialLoginButton(
                text = "Continue with Facebook",
                icon = R.drawable.ic_facebook,
                onClick = onFacebookClick
            )

            Spacer(modifier = Modifier.weight(1f))

            BottomAuthText(
                onSignUpClick = onSignUp
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}