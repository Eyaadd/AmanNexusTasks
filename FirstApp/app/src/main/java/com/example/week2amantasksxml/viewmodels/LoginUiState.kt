package com.example.week2amantasksxml.viewmodels

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isChecked: Boolean = false,
    val idle: Boolean = false,
    val errorMessage: String? = null,
    val loading: Boolean = false,
    val isLoggedIn: Boolean = false
)