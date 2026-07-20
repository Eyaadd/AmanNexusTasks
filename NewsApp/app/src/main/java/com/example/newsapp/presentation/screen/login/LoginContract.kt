package com.example.newsapp.presentation.screen.login


object LoginContract {

    data class State(
        val email: String = "",
        val password: String = "",
        val emailError: String? = null,
        val passwordError: String? = null,
        val isPasswordVisible: Boolean = false,
        val isLoading: Boolean = false
    )

    sealed interface Intent {

        data class EmailChanged(
            val email: String
        ) : Intent

        data class PasswordChanged(
            val password: String
        ) : Intent

        data object TogglePasswordVisibility : Intent

        data object LoginClicked : Intent
    }

    sealed interface Effect {

        data object NavigateToHome : Effect

        data class ShowError(
            val message: String
        ) : Effect
    }
}