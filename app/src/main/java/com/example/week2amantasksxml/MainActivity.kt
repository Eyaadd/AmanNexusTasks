package com.example.week2amantasksxml

import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.week2amantasksxml.viewmodels.loader.ImageLoaderScreen
import com.example.week2amantasksxml.viewmodels.loader.ImageLoaderScreenContent


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // For XML View

//        setContentView(R.layout.activity_main)
        // Compose View

        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                ImageLoaderScreen(modifier = Modifier.padding(innerPadding))
            }

        }

    }
}




fun View.applySystemBarsPadding() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

        view.setPadding(
            systemBars.left,
            systemBars.top,
            systemBars.right,
            systemBars.bottom
        )

        insets
    }
}