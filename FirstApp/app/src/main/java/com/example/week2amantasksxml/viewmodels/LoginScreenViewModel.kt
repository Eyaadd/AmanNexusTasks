package com.example.week2amantasksxml.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week2amantasksxml.viewmodels.LoginUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

class LoginScreenViewModel : ViewModel() {

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


    fun computeHeavyOperation() {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                repeat(100_000_000) { iteration ->
                    ensureActive()

                    val result = iteration * iteration
                }

                println("Finished normally")

            } catch (e: CancellationException) {
                println("Cancelled!")
                throw e
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("ViewModel cleared")
    }
}