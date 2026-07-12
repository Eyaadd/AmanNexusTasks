package com.example.week2amantasksxml.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week2amantasksxml.viewmodel.CounterScreenViewModel

@Composable
fun CounterScreen(modifier: Modifier = Modifier, viewModel: CounterScreenViewModel = viewModel()) {

    val counter by viewModel.counter.collectAsStateWithLifecycle()
    Column( modifier = modifier
            .fillMaxSize()
            .background(
                when (counter % 4) {
                    0 -> Color.Red
                    1 -> Color.Green
                    2 -> Color.Blue
                    else -> Color.Yellow
                }
            ),
        Arrangement.Center,
        Alignment.CenterHorizontally

    ) {
        Text(
            "$counter"
        )
        Button(onClick = { viewModel.updateCounter() }) {
            Text("Increment And Change Background Color")
        }

    }


}


@Preview
@Composable
fun CounterScreenPreview() {
    CounterScreen()
}

