package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.todoapp.presentation.screens.home.HomeScreen
import com.example.todoapp.presentation.screens.home.HomeViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val application = application as TodoApplication

        val homeViewModelFactory = HomeViewModelFactory(
            repository = application.taskRepository
        )

        setContent {
                HomeScreen(
                    homeViewModelFactory = homeViewModelFactory
                )

        }
    }
}