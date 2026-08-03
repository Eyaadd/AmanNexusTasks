package com.example.todoapp

import android.app.Application
import com.example.todoapp.data.repository.TaskRepository
import com.example.todoapp.data.repository.TaskRepositoryImp
import com.example.todoapp.data.source.local.database.TaskDatabase

class TodoApplication : Application() {

    val database: TaskDatabase by lazy {
        TaskDatabase.getInstance(applicationContext)
    }

    val taskRepository: TaskRepository by lazy {
        TaskRepositoryImp(
            taskDao = database.taskDao()
        )
    }
}