package com.example.todoapp.presentation.screens.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.data.source.local.entity.TaskEntity
import com.example.todoapp.presentation.screens.home.components.AddTaskSection
import com.example.todoapp.presentation.screens.home.components.TodoItem
import com.example.todoapp.presentation.screens.home.components.TodoSummaryCard
import com.example.todoapp.presentation.theme.checkboxColors
import com.example.todoapp.presentation.theme.screenColor

@Composable
fun HomeScreen(
    homeViewModelFactory: HomeViewModelFactory
) {
    val viewModel: HomeViewModel = viewModel(
        factory = homeViewModelFactory
    )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        uiState = uiState, onIntent = viewModel::onIntent
    )
}

@Composable
fun HomeScreenContent(
    uiState: HomeContract.HomeState,
    onIntent: (HomeContract.Intent) -> Unit,
    modifier: Modifier = Modifier
) {


    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(screenColor)
                .padding(innerPadding)
                .padding(
                    horizontal = 14.dp, vertical = 32.dp
                )
        ) {
            TodoSummaryCard()

            Spacer(modifier = Modifier.size(18.dp))

            AddTaskSection(
                taskTitle = uiState.taskTitle, onTaskTitleChange = { newTitle ->
                onIntent(
                    HomeContract.Intent.OnTaskTitleChanged(
                        title = newTitle
                    )
                )
            }, onAddTaskClick = {
                onIntent(
                    HomeContract.Intent.InsertTask(
                        uiState.taskTitle, uiState.taskDescription
                    )
                )

            }, placeholder = "Enter a new title"
            )
            Spacer(Modifier.size(8.dp))
            AddTaskSection(
                taskTitle = uiState.taskDescription ?: "",
                onTaskTitleChange = { newDescription ->
                    onIntent(
                        HomeContract.Intent.OnTaskDescriptionChanged(
                            description = newDescription
                        )
                    )
                },
                onAddTaskClick = {
                    onIntent(
                        HomeContract.Intent.InsertTask(
                            uiState.taskTitle, uiState.taskDescription
                        )
                    )

                },
                placeholder = "Enter a new description"
            )

            Spacer(modifier = Modifier.size(44.dp))

            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                uiState.errorMessage != null -> {
                    Text(
                        text = uiState.errorMessage, color = Color.White
                    )
                }

                uiState.taskList.isEmpty() -> {
                    Text(
                        text = "No tasks yet"
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(
                            items = uiState.taskList, key = { _, task ->
                                task.id
                            }) { index, task ->
                            TodoItem(
                                task = task,
                                checkBoxColor = checkboxColors[index % checkboxColors.size],
                                onToggleClick = {
                                    onIntent(HomeContract.Intent.ToggleTaskCompletion(task))
                                },
                                onDeleteClick = {
                                    onIntent(HomeContract.Intent.DeleteTask(task))
                                })
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
private fun HomeScreenPreview() {
    HomeScreenContent(
        uiState = HomeContract.HomeState(
            isLoading = false, taskList = listOf(
                TaskEntity(
                    id = 1,
                    title = "Complete Room implementation",
                    isCompleted = true,
                    description = "This is a test"
                ), TaskEntity(
                    id = 2,
                    title = "Create HomeViewModel",
                    isCompleted = false,
                    description = "This is a test"

                ), TaskEntity(
                    id = 3,
                    title = "Review Pull Request",
                    isCompleted = true,
                    description = "This is a test"

                ), TaskEntity(
                    id = 4,
                    title = "Design Todo UI",
                    isCompleted = false,
                    description = "This is a test"

                ), TaskEntity(
                    id = 5,
                    title = "Write unit tests",
                    isCompleted = false,
                    description = "This is a test"

                ), TaskEntity(
                    id = 6,
                    title = "Refactor repository layer",
                    isCompleted = true,
                    description = "This is a test"

                ), TaskEntity(
                    id = 7,
                    title = "Prepare presentation",
                    isCompleted = false,
                    description = "This is a test"

                ), TaskEntity(
                    id = 8,
                    title = "Deploy application",
                    isCompleted = false,
                    description = "This is a test"

                )
            ), errorMessage = null,
            taskDescription = ""
        ), onIntent = {},)
}