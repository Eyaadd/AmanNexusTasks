package com.example.todoapp.data.repository

import com.example.todoapp.data.source.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun observeTasks(): Flow<List<TaskEntity>>

    suspend fun insertTask(task: TaskEntity): Long

    suspend fun deleteTask(task: TaskEntity)

    suspend fun updateTask(task: TaskEntity)
}