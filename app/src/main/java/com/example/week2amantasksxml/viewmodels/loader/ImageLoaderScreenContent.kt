package com.example.week2amantasksxml.viewmodels.loader

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week2amantasksxml.R
import com.example.week2amantasksxml.viewmodels.ImageLoaderUiState
import com.example.week2amantasksxml.viewmodels.ImageLoaderViewModel

@Composable
fun ImageLoaderScreen(
    modifier: Modifier = Modifier,
    viewModel: ImageLoaderViewModel = viewModel()

){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ImageLoaderScreenContent(
        uiState = uiState,
        modifier = modifier,
        onClick = {viewModel.loadImage()}
    ){
        viewModel.cancelLoading()
    }

}



@Composable
fun ImageLoaderScreenContent(
    uiState : ImageLoaderUiState,
    modifier: Modifier = Modifier,
    onClick : () -> Unit,
    onCancel : () -> Unit
) {


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (uiState.isLoaded) {

            Image(
                painter = painterResource(R.drawable.ic_person),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )

        } else {

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {

                Text("No Image")
            }
        }

        Spacer(Modifier.height(24.dp))

        if (uiState.isLoading) {

            LinearProgressIndicator(
                progress = { uiState.progress / 100f },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            Text("${uiState.progress}%")
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onClick,
            enabled = !uiState.isLoading
        ) {
            Text("Load Image")
        }

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = onCancel,
            enabled = uiState.isLoading
        ) {
            Text("Cancel")
        }
    }
}

