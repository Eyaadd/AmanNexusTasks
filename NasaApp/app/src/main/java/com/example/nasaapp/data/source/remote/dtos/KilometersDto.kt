package com.example.nasaapp.data.source.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class KilometersDto(
    val estimatedDiameterMin: Double,
    val estimatedDiameterMax: Double
)