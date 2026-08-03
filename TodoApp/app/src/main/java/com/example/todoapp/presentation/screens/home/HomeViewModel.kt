package com.example.todoapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.repository.TaskRepository
import com.example.todoapp.data.source.local.entity.TaskEntity
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeContract.HomeState())
    val uiState = _uiState.asStateFlow()

    fun onIntent(intent: HomeContract.Intent) {
        when (intent) {
            is HomeContract.Intent.DeleteTask -> deleteTask(intent.task)
            is HomeContract.Intent.InsertTask -> insertTask(intent.title, intent.description)
            is HomeContract.Intent.ToggleTaskCompletion -> updateTaskCompletion(intent.task)
            is HomeContract.Intent.OnTaskTitleChanged -> {
                _uiState.update {
                    it.copy(taskTitle = intent.title)
                }
            }

            is HomeContract.Intent.OnTaskDescriptionChanged -> _uiState.update {
                it.copy(taskDescription = intent.description)
            }
        }
    }

    init {
        observeTasks()
    }

    private fun observeTasks() {
        viewModelScope.launch {
            repository.observeTasks().onStart {
                updateLoadingState()
            }.catch { exception ->

                if (exception is CancellationException) {
                    throw exception
                }
                updateErrorState(exception.message)

            }

                .collect { tasks ->
                    updateSuccessState(tasks)
                }
        }

    }

    private fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            try {
                repository.deleteTask(task)
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                updateErrorState(
                    exception.message
                )
            }
        }
    }

    private fun insertTask(title: String, description: String) {
        val trimmedTitle = title.trim()
        val trimmedDescription = description.trim()
        if (trimmedTitle.isBlank() || trimmedDescription.isBlank()) {
            updateErrorState("Task title or description cannot be empty")
            return
        }
        val task = TaskEntity(
            title = trimmedTitle, description = trimmedDescription
        )
        viewModelScope.launch {
            try {
                repository.insertTask(task)
                _uiState.update {
                    it.copy(taskTitle = "", taskDescription = "")
                }
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                updateErrorState(
                    exception.message
                )
            }
        }
    }

    private fun updateTaskCompletion(task: TaskEntity) {
        viewModelScope.launch {
            try {
                val updatedTask = task.copy(
                    isCompleted = !task.isCompleted
                )

                repository.updateTask(updatedTask)
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                updateErrorState(
                    exception.message
                )
            }
        }
    }

    private fun updateLoadingState() {
        _uiState.update {
            it.copy(
                isLoading = true, errorMessage = null
            )
        }
    }

    private fun updateSuccessState(tasks: List<TaskEntity>) {
        _uiState.update {
            it.copy(
                taskList = tasks, isLoading = false, errorMessage = null
            )
        }
    }

    private fun updateErrorState(message: String?) {
        _uiState.update {
            it.copy(
                errorMessage = message ?: "Failed to load tasks", isLoading = false
            )
        }
    }
}