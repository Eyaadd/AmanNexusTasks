package com.example.nasaapp.data.source.remote.dtos

import com.google.gson.annotations.SerializedName

data class RelativeVelocityDto(

    @SerializedName("kilometers_per_hour")
    val kilometersPerHour: String?
)