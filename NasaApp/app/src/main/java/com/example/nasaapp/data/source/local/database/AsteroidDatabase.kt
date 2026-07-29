
package com.example.nasaapp.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {

        @Volatile
        private var INSTANCE: AsteroidDatabase? = null

        fun getInstance(context: Context): AsteroidDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AsteroidDatabase::class.java,
                    name = "asteroid_database"
                )
                    .build()
                INSTANCE = instance

                instance
            }
        }

    }
}