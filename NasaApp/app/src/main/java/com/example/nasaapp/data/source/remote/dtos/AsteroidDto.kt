package com.example.nasaapp.data.source.remote.dtos

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AsteroidDto(

    @SerialName("id")
    val id: String?,

    @SerialName("name")
    val name: String?,

    @SerialName("estimated_diameter")
    val estimatedDiameter: EstimatedDiameterDto?,

    @SerialName("is_potentially_hazardous_asteroid")
    val isPotentiallyHazardousAsteroid: Boolean?,

    @SerialName("close_approach_data")
    val closeApproachData: List<CloseApproachDto>?
)