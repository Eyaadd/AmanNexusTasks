package com.example.week2amantasksxml.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class ImageLoaderViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ImageLoaderUiState())
    val uiState: StateFlow<ImageLoaderUiState> = _uiState.asStateFlow()

    private var loadingJob: Job? = null

    fun loadImage() {

        if (loadingJob?.isActive == true) return

        loadingJob = viewModelScope.launch {

            onStartState()

            try {

                for (progress in 1..100) {

                    delay(50)

                    updateProgressState(progress)
                }

                onSuccessState()

            } catch (e: CancellationException) {

              onCancelState()

                throw e
            }
        }
    }

     fun cancelLoading() {
        loadingJob?.cancel()
    }

    private fun updateProgressState(progress: Int) {
        _uiState.update {
            it.copy(progress = progress)
        }
    }

    private fun onStartState() {
        _uiState.update {
            it.copy(
                progress = 0,
                isLoading = true,
                isLoaded = false
            )
        }
    }

    private fun onSuccessState() {
        _uiState.update {
            it.copy(
                isLoading = false,
                isLoaded = true
            )
        }
    }

    private fun onCancelState() {

        _uiState.update {
            it.copy(
                progress = 0,
                isLoading = false,
                isLoaded = false
            )
        }
    }
}