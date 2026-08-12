package com.example.recipeapp.presentation.screens.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipeapp.R
import com.example.recipeapp.presentation.screens.auth.components.AuthSubmitButton
import com.example.recipeapp.presentation.screens.auth.components.AuthTextField
import com.example.recipeapp.presentation.theme.RecipeAppTheme
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(viewModel) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                SignUpContract.SignUpEffect.NavigateToHome -> onNavigateToHome()
                SignUpContract.SignUpEffect.NavigateToLogin -> onNavigateToLogin()
            }
        }
    }
    SignUpScreenContent(state, viewModel::onIntent, modifier)
}

@Composable
fun SignUpScreenContent(
    state: SignUpContract.SignUpState,
    onIntent: (SignUpContract.SignUpIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.auth_create_account), fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Text(stringResource(R.string.auth_signup_subtitle), color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(28.dp))
        AuthTextField(state.email, { onIntent(SignUpContract.SignUpIntent.OnEmailChanged(it)) }, R.string.auth_email)
        Spacer(Modifier.height(12.dp))
        AuthTextField(state.password, { onIntent(SignUpContract.SignUpIntent.OnPasswordChanged(it)) }, R.string.auth_password, isPassword = true, isPasswordVisible = state.isPasswordVisible, onPasswordVisibilityClick = { onIntent(SignUpContract.SignUpIntent.OnPasswordVisibilityClicked) })
        Spacer(Modifier.height(12.dp))
        AuthTextField(state.confirmPassword, { onIntent(SignUpContract.SignUpIntent.OnConfirmPasswordChanged(it)) }, R.string.auth_confirm_password, isPassword = true, isPasswordVisible = state.isPasswordVisible, onPasswordVisibilityClick = { onIntent(SignUpContract.SignUpIntent.OnPasswordVisibilityClicked) })
        state.errorMessage?.let { Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 12.dp)) }
        AuthSubmitButton(R.string.auth_sign_up, state.isLoading, { onIntent(SignUpContract.SignUpIntent.OnSignUpClicked) }, Modifier.padding(top = 20.dp))
        TextButton(onClick = { onIntent(SignUpContract.SignUpIntent.OnLoginClicked) }) {
            Text(stringResource(R.string.auth_have_account))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    RecipeAppTheme { SignUpScreenContent(SignUpContract.SignUpState(), {}) }
}
