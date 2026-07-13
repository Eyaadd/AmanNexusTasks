package com.example.week2amantasksxml.viewmodels

data class ImageLoaderUiState(
    val progress: Int = 0,
    val isLoading: Boolean = false,
    val isLoaded: Boolean = false
)