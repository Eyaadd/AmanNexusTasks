package com.example.nasaapp.presentation.mapper

import com.example.nasaapp.data.model.AsteroidUiModel
import com.example.nasaapp.data.source.local.entity.AsteroidEntity
import java.text.NumberFormat
import java.util.Locale

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