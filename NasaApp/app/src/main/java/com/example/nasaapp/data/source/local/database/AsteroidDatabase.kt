
package com.example.nasaapp.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nasaapp.data.source.local.dao.AsteroidDao
import com.example.nasaapp.data.source.local.entity.AsteroidEntity

@Database(
    entities = [AsteroidEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AsteroidDatabase : RoomDatabase() {

    abstract fun asteroidDao(): AsteroidDao


}