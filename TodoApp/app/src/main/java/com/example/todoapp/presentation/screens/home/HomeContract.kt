package com.example.todoapp.presentation.screens.home

import com.example.todoapp.data.source.local.entity.TaskEntity

object HomeContract {

    data class HomeState(
        val isLoading: Boolean = false,
        val taskList: List<TaskEntity> = emptyList(),
        val taskTitle: String = "",
        val taskDescription: String? = null,
        val errorMessage: String? = null
    )


    sealed interface Intent {

        data class OnTaskTitleChanged(
            val title: String
        ) : Intent

        data class OnTaskDescriptionChanged(
            val description: String
        ) : Intent

        data class InsertTask(
            val title: String,
            val description: String?
        ) : Intent

        data class ToggleTaskCompletion(
            val task: TaskEntity
        ) : Intent

        data class DeleteTask(
            val task: TaskEntity
        ) : Intent

    }
}