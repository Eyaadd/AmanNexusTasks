package com.example.nasaapp.data.source.remote.dtos

import com.google.gson.annotations.SerializedName

data class CloseApproachDto(

    @SerializedName("close_approach_date")
    val closeApproachDate: String?,

    @SerializedName("relative_velocity")
    val relativeVelocity: RelativeVelocityDto?,

    @SerializedName("miss_distance")
    val missDistance: MissDistanceDto?
)