package com.example.nasaapp.data.source.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class LinksDto(
    val next: String,
    val previous: String,
    val self: String
)