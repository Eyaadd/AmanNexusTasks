package com.example.nasaapp.data.source.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asteroids")
data class AsteroidEntity(
    @PrimaryKey
    val id: String ,
    val name: String,
    val closeApproachDate: String,
    val estimatedDiameterMinKm: Double,
    val estimatedDiameterMaxKm: Double,
    val relativeVelocityKmPerHour: Double,
    val missDistanceKm: Double,
    val isPotentiallyHazardous: Boolean
)