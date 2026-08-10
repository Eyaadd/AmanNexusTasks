package com.example.nasaapp.data.source.remote.dtos

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RelativeVelocityDto(

    @SerialName("kilometers_per_hour")
    val kilometersPerHour: String?
)