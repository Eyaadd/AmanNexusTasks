package com.example.recipeapp.presentation.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.usecase.SignUpUseCase
import com.example.recipeapp.presentation.mapper.toUiMessage
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpContract.SignUpState())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<SignUpContract.SignUpEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: SignUpContract.SignUpIntent) {
        when (intent) {
            is SignUpContract.SignUpIntent.OnEmailChanged -> _uiState.update { it.copy(email = intent.email, errorMessage = null) }
            is SignUpContract.SignUpIntent.OnPasswordChanged -> _uiState.update { it.copy(password = intent.password, errorMessage = null) }
            is SignUpContract.SignUpIntent.OnConfirmPasswordChanged -> _uiState.update { it.copy(confirmPassword = intent.password, errorMessage = null) }
            SignUpContract.SignUpIntent.OnPasswordVisibilityClicked -> _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            SignUpContract.SignUpIntent.OnSignUpClicked -> signUp()
            SignUpContract.SignUpIntent.OnLoginClicked -> viewModelScope.launch {
                _effect.send(SignUpContract.SignUpEffect.NavigateToLogin)
            }
        }
    }

    private fun signUp() {
        val state = _uiState.value
        val validationError = when {
            state.email.isBlank() || state.password.isBlank() -> "Email and password are required."
            state.password != state.confirmPassword -> "Passwords do not match."
            else -> null
        }
        if (validationError != null) {
            _uiState.update { it.copy(errorMessage = validationError) }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            signUpUseCase(state.email.trim(), state.password)
                .onSuccess {
                    _uiState.update { it.copy(isLoading = false) }
                    _effect.send(SignUpContract.SignUpEffect.NavigateToHome)
                }
                .onFailure { error ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = error.toUiMessage()) }
                }
        }
    }
}
