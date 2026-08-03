package com.example.nasaapp.data.source.remote.dtos

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AsteroidsDto(
    @SerialName("element_count")
    val elementCount: Int,
    val links: LinksDto,
    @SerialName("near_earth_objects")
    val nearEarthObjects: Map<String, List<AsteroidDto>>
)