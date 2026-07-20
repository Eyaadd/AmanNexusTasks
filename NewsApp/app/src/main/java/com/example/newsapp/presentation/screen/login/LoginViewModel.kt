package com.example.newsapp.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.auth.FakeLoginRepository
import com.example.newsapp.data.repository.auth.LoginRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository = FakeLoginRepository()

) : ViewModel() {

    private val _state = MutableStateFlow(LoginContract.State())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<LoginContract.Effect>()
    val effect = _effect.asSharedFlow()

    fun onIntent(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.EmailChanged -> {
                onEmailChanged(intent.email)
            }

            LoginContract.Intent.LoginClicked ->login()
            is LoginContract.Intent.PasswordChanged -> {
                onPasswordChanged(intent.password)
            }

            LoginContract.Intent.TogglePasswordVisibility -> togglePasswordVisibility()
        }
    }

    private fun onEmailChanged(email: String) {
        _state.update {
            it.copy(
                email = email, emailError = null
            )
        }
    }

    private fun onPasswordChanged(password: String) {
        _state.update {
            it.copy(
                password = password, passwordError = null
            )
        }
    }

    private fun togglePasswordVisibility() {
        _state.update {
            it.copy(
                isPasswordVisible = !it.isPasswordVisible
            )
        }
    }

    private fun login() {

        val currentState = _state.value

        val emailError = validateEmail(currentState.email)
        val passwordError = validatePassword(currentState.password)

        if (emailError != null || passwordError != null) {
            _state.update {
                it.copy(
                    emailError = emailError,
                    passwordError = passwordError
                )
            }
            return
        }

        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            val isLoginSuccessful = repository.login(
                email = currentState.email,
                password = currentState.password
            )

            if (isLoginSuccessful) {
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
                _effect.emit(
                    LoginContract.Effect.NavigateToHome
                )
            } else {
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
                _effect.emit(
                    LoginContract.Effect.ShowError(
                        "Invalid email or password"

                    )
                )
            }
        }
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


    private fun validatePassword(password: String): String? {

        if (password.isBlank()) return "Password is required"
        return null
    }

}