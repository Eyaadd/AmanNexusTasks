package com.example.week2amantasksxml.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun MenuScreen(modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(1000){
            Text("Item $it")
        }
    }
}


@Preview
@Composable
fun MenuScreenPreview(){
    MenuScreen()
}