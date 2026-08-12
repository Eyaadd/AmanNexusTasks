package com.example.recipeapp.presentation.screens.signup

object SignUpContract {
    data class SignUpState(
        val email: String = "",
        val password: String = "",
        val confirmPassword: String = "",
        val isPasswordVisible: Boolean = false,
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

    sealed interface SignUpIntent {
        data class OnEmailChanged(val email: String) : SignUpIntent
        data class OnPasswordChanged(val password: String) : SignUpIntent
        data class OnConfirmPasswordChanged(val password: String) : SignUpIntent
        data object OnPasswordVisibilityClicked : SignUpIntent
        data object OnSignUpClicked : SignUpIntent
        data object OnLoginClicked : SignUpIntent
    }

    sealed interface SignUpEffect {
        data object NavigateToHome : SignUpEffect
        data object NavigateToLogin : SignUpEffect
    }
}
