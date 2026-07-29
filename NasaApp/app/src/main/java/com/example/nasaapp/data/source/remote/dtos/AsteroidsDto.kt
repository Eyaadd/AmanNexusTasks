package com.example.nasaapp.data.source.remote.dtos

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class AsteroidsDto(
    @SerializedName("element_count")
    val elementCount: Int,
    val links: LinksDto,
    @SerializedName("near_earth_objects")
    val nearEarthObjects: Map<String, List<AsteroidDto>>
)