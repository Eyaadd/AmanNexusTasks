package com.example.newsapp.ui.login.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.newsapp.R
import com.example.newsapp.theme.activeButtonColor
import com.example.newsapp.theme.darkGray
import com.example.newsapp.theme.disabledButtonColor
import com.example.newsapp.theme.roboto
import com.example.newsapp.theme.sourceSans
import com.example.newsapp.ui.login.LoginContract
import com.example.newsapp.ui.login.components.BottomAuthText
import com.example.newsapp.ui.login.components.CustomOutlinedTextField
import com.example.newsapp.ui.login.components.SocialLoginButton
import com.example.newsapp.ui.login.viewmodel.LoginViewModel


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
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                LoginContract.Effect.NavigateToHome -> onLoginSuccess()

                is LoginContract.Effect.ShowError -> snackbarHostState.showSnackbar(message = effect.message)

            }
        }
    }
    LoginScreenContent(
        state = state,
        snackbarHostState = snackbarHostState,
        onIntent = viewModel::onIntent,
        onForgotPassword = onForgotPassword,
        onSignUp = onSignUp,
        onGoogleClick = onGoogleClick,
        onFacebookClick = onFacebookClick,
        modifier = modifier
    )
}


@Composable
fun LoginScreenContent(
    state: LoginContract.State,
    snackbarHostState: SnackbarHostState,
    onIntent: (LoginContract.Intent) -> Unit,
    onForgotPassword: () -> Unit,
    onSignUp: () -> Unit,
    onGoogleClick: () -> Unit,
    onFacebookClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
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
                value = state.email,
                onValueChange = { email ->
                    onIntent(
                        LoginContract.Intent.EmailChanged(email)
                    )
                },
                placeHolder = "Email",
                leadingIcon = R.drawable.ic_sms,
                isError = state.emailError != null,
                errorMessage = state.emailError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomOutlinedTextField(
                value = state.password,
                onValueChange = { password ->
                    onIntent(
                        LoginContract.Intent.PasswordChanged(password)
                    )
                },
                placeHolder = "Password",
                leadingIcon = R.drawable.ic_lock,
                trailingIcon = if (state.isPasswordVisible) {
                    R.drawable.eye_off
                } else {
                    R.drawable.ic_eye
                },
                onTrailingIconClick = {
                    onIntent(
                        LoginContract.Intent.TogglePasswordVisibility
                    )
                },
                isError = state.passwordError != null,
                errorMessage = state.passwordError,
                visualTransformation = if (state.isPasswordVisible) {
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
                enabled = !state.isLoading,
                onClick = {
                    onIntent(
                        LoginContract.Intent.LoginClicked
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = activeButtonColor,
                    disabledContainerColor = disabledButtonColor,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                if (state.isLoading) {
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
    LoginScreenContent(
        state = LoginContract.State(),
        snackbarHostState = remember {
            SnackbarHostState()
        },
        onIntent = {},
        onForgotPassword = {},
        onSignUp = {},
        onGoogleClick = {},
        onFacebookClick = {}
    )
}