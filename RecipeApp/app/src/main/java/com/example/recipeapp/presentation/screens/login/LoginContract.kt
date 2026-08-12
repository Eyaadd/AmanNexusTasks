package com.example.recipeapp.presentation.screens.login

object LoginContract {
    data class LoginState(
        val email: String = "",
        val password: String = "",
        val isPasswordVisible: Boolean = false,
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

    sealed interface LoginIntent {
        data class OnEmailChanged(val email: String) : LoginIntent
        data class OnPasswordChanged(val password: String) : LoginIntent
        data object OnPasswordVisibilityClicked : LoginIntent
        data object OnLoginClicked : LoginIntent
        data object OnSignUpClicked : LoginIntent
    }

    sealed interface LoginEffect {
        data object NavigateToHome : LoginEffect
        data object NavigateToSignUp : LoginEffect
    }
}
