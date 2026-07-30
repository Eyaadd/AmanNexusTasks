package com.example.nasaapp.data.source.remote.dtos

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CloseApproachDto(

    @SerialName("close_approach_date")
    val closeApproachDate: String?,

    @SerialName("relative_velocity")
    val relativeVelocity: RelativeVelocityDto?,

    @SerialName("miss_distance")
    val missDistance: MissDistanceDto?
)