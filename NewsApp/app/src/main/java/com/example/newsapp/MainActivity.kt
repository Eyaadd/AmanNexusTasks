package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.navigation.AppNavHost
import com.example.newsapp.presentation.screen.post.PostsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            val navController = rememberNavController()
//
//            AppNavHost(
//                navController = navController
//            )
            Scaffold() { innerPadding ->
                PostsScreen(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

