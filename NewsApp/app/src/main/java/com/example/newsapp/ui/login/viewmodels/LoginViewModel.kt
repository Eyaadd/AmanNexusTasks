package com.example.newsapp.ui.login.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _formState = MutableStateFlow(LoginFormState())
    val formState: StateFlow<LoginFormState> = _formState.asStateFlow()

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChanged(email: String) {
        _formState.update {
            it.copy(
                email = email,
                emailError = null
            )
        }
    }

    fun onPasswordChanged(password: String) {
        _formState.update {
            it.copy(
                password = password,
                passwordError = null
            )
        }
    }

    fun togglePasswordVisibility() {
        _formState.update {
            it.copy(
                isPasswordVisible = !it.isPasswordVisible
            )
        }
    }

    fun login() {

        val form = _formState.value

        val emailError = validateEmail(form.email)

        if (emailError != null) {
            _formState.update {
                it.copy(emailError = emailError)
            }
            return
        }

        viewModelScope.launch {

            _uiState.value = LoginUiState.Loading

            delay(2000)

            if (
                form.email == "admin@test.com" &&
                form.password == "123456"
            ) {
                _uiState.value = LoginUiState.Success
            } else {
                _uiState.value = LoginUiState.Error(
                    "Invalid email or password"
                )
            }
        }
    }

    fun resetUiState() {
        _uiState.value = LoginUiState.Idle
    }

    private fun validateEmail(email: String): String? {

        val emailRegex = Regex(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        )

        return when {
            email.isBlank() -> "Email is required"
            !emailRegex.matches(email) -> "Invalid email address"
            else -> null
        }
    }

}