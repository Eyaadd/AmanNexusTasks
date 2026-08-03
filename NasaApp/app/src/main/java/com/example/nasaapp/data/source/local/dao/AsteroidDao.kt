package com.example.nasaapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nasaapp.data.source.local.entity.AsteroidEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM asteroids ORDER BY closeApproachDate ASC")
    fun observeAsteroids(): Flow<List<AsteroidEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsteroids(
        asteroids: List<AsteroidEntity>
    )

    @Query("DELETE FROM asteroids")
    suspend fun deleteAllAsteroids()
}