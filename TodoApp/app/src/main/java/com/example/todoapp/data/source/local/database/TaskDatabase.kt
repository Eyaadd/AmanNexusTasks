package com.example.todoapp.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.data.source.local.dao.TaskDao
import com.example.todoapp.data.source.local.entity.TaskEntity

@Database(
    entities = [TaskEntity::class], version = 2, exportSchema = false
)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {

        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = TaskDatabase::class.java,
                    name = "task_database"
                ).addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance

                instance
            }
        }

    }
}

