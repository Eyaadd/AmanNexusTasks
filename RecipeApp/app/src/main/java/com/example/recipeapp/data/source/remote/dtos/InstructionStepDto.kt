package com.example.recipeapp.data.source.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class InstructionStepDto(
    val number: Int,
    val step: String
)