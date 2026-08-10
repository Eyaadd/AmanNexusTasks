package com.example.recipeapp.data.source.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class AnalyzedInstructionDto(
    val name: String = "",
    val steps: List<InstructionStepDto> = emptyList()
)