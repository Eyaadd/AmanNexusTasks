package com.example.recipeapp.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.usecase.LoginUseCase
import com.example.recipeapp.presentation.mapper.toUiMessage
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginContract.LoginState())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<LoginContract.LoginEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: LoginContract.LoginIntent) {
        when (intent) {
            is LoginContract.LoginIntent.OnEmailChanged -> _uiState.update {
                it.copy(email = intent.email, errorMessage = null)
            }
            is LoginContract.LoginIntent.OnPasswordChanged -> _uiState.update {
                it.copy(password = intent.password, errorMessage = null)
            }
            LoginContract.LoginIntent.OnPasswordVisibilityClicked -> _uiState.update {
                it.copy(isPasswordVisible = !it.isPasswordVisible)
            }
            LoginContract.LoginIntent.OnLoginClicked -> login()
            LoginContract.LoginIntent.OnSignUpClicked -> viewModelScope.launch {
                _effect.send(LoginContract.LoginEffect.NavigateToSignUp)
            }
        }
    }

    private fun login() {
        val state = _uiState.value
        if (state.email.isBlank() || state.password.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Email and password are required.") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            loginUseCase(state.email.trim(), state.password)
                .onSuccess {
                    _uiState.update { it.copy(isLoading = false) }
                    _effect.send(LoginContract.LoginEffect.NavigateToHome)
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = error.toUiMessage())
                    }
                }
        }
    }
}
