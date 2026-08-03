package com.example.todoapp.data.repository

import com.example.todoapp.data.source.local.dao.TaskDao
import com.example.todoapp.data.source.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImp(
    private val taskDao: TaskDao
) : TaskRepository {
    override fun observeTasks(): Flow<List<TaskEntity>> = taskDao.observeTasks()


    override suspend fun insertTask(task: TaskEntity): Long = taskDao.insertTask(task)


    override suspend fun deleteTask(task: TaskEntity) = taskDao.deleteTask(task)


    override suspend fun updateTask(task: TaskEntity) = taskDao.updateTask(task)

}