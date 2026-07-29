package com.example.nasaapp.data.source.remote.mapper

import com.example.nasaapp.data.model.AsteroidUiModel
import com.example.nasaapp.data.source.local.entity.AsteroidEntity
import com.example.nasaapp.data.source.remote.dtos.AsteroidDto
import java.text.NumberFormat
import java.util.Locale

fun AsteroidDto.toEntity(): AsteroidEntity? {
    val closeApproach = closeApproachData
        ?.firstOrNull()
        ?: return null

    val closeApproachDate = closeApproach.closeApproachDate
        ?: return null

    val relativeVelocityKmPerHour =
        closeApproach.relativeVelocity
            ?.kilometersPerHour
            ?.toDoubleOrNull()
            ?: return null

    val missDistanceKm =
        closeApproach.missDistance
            ?.kilometers
            ?.toDoubleOrNull()
            ?: return null

    val diameterMin =
        estimatedDiameter
            ?.kilometers
            ?.estimatedDiameterMin
            ?: return null

    val diameterMax =
        estimatedDiameter
            ?.kilometers
            ?.estimatedDiameterMax
            ?: return null

    return AsteroidEntity(
        id = id ?: return null,
        name = name ?: return null,
        closeApproachDate = closeApproachDate,
        estimatedDiameterMinKm = diameterMin,
        estimatedDiameterMaxKm = diameterMax,
        relativeVelocityKmPerHour = relativeVelocityKmPerHour,
        missDistanceKm = missDistanceKm,
        isPotentiallyHazardous = isPotentiallyHazardousAsteroid ?: false
    )
}



fun AsteroidEntity.toUiModel(): AsteroidUiModel {
    return AsteroidUiModel(
        id = id,
        name = name,
        closeApproachDate = closeApproachDate,
        estimatedDiameterRange = buildDiameterRange(),
        relativeVelocity = "${relativeVelocityKmPerHour.formatNumber()} km/h",
        missDistance = "${missDistanceKm.formatNumber()} km",
        hazardStatus = if (isPotentiallyHazardous) {
            "Potentially Hazardous"
        } else {
            "Not Hazardous"
        },
        isPotentiallyHazardous = isPotentiallyHazardous
    )
}

private fun AsteroidEntity.buildDiameterRange(): String {
    val min = estimatedDiameterMinKm.formatDecimal()
    val max = estimatedDiameterMaxKm.formatDecimal()

    return "$min - $max km"
}

private fun Double.formatNumber(): String {
    return NumberFormat
        .getNumberInstance(Locale.US)
        .format(this)
}

private fun Double.formatDecimal(): String {
    return String.format(
        Locale.US,
        "%.3f",
        this
    )
}


