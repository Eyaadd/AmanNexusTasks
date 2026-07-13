package com.example.week2amantasksxml.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()
    private val emailRegex =
        Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")


    fun login() {
        viewModelScope.launch {

            updateLoadingState()

            delay(5000)

            val currentState = _uiState.value

            val isValid =
                isValidEmail(currentState.email) &&
                        currentState.password.isNotBlank()

            if (isValid) {
                updateSuccessState()
            } else {
                updateErrorState()
            }
        }
    }

    fun onEmailChange(value: String) {
        _uiState.update {
            it.copy(
                email = value
            )
        }
    }

    fun onPasswordChange(value: String) {
        _uiState.update {
            it.copy(
                password = value
            )
        }
    }

    fun onCheckChanged() {
        _uiState.update {
            it.copy(
                isChecked = !it.isChecked
            )
        }
    }

    fun resetLoginState() {
        _uiState.update {
            it.copy(
                isLoggedIn = false
            )
        }
    }

    private fun updateSuccessState() {
        _uiState.update {
            it.copy(
                loading = false,
                isLoggedIn = true,
                errorMessage = null
            )
        }
    }

    private fun updateErrorState() {
        _uiState.update {
            it.copy(
                loading = false,
                errorMessage = "Enter a valid email and password."
            )
        }
    }

    private fun updateLoadingState() {
        _uiState.update {
            it.copy(
                loading = true,
                errorMessage = null
            )
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return emailRegex.matches(email)
    }

}