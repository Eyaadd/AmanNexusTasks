package com.example.nasaapp.data.source.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class EstimatedDiameterDto(
    val kilometers: KilometersDto
)