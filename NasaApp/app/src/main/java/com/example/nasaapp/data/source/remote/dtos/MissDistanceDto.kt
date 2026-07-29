package com.example.nasaapp.data.source.remote.dtos

import com.google.gson.annotations.SerializedName

data class MissDistanceDto(

    @SerializedName("kilometers")
    val kilometers: String?
)