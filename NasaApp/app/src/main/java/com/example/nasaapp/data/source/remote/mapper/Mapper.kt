package com.example.nasaapp.data.source.remote.mapper

import com.example.nasaapp.data.source.local.entity.AsteroidEntity
import com.example.nasaapp.data.source.remote.dtos.AsteroidDto

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






