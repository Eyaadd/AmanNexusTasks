package com.example.nasaapp.data.model

data class AsteroidUiModel(
    val id: String,
    val name: String,
    val closeApproachDate: String,
    val estimatedDiameterRange: String,
    val relativeVelocity: String,
    val missDistance: String,
    val hazardStatus: String,
    val isPotentiallyHazardous: Boolean
)