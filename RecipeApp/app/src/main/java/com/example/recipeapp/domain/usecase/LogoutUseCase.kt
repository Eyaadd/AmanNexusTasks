package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.repository.AuthRepository

class LogoutUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke() = authRepository.logout()
}