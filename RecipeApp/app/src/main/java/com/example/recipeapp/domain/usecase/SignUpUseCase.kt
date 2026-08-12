package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.repository.AuthRepository

class SignUpUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        authRepository.signUp(email, password)
}