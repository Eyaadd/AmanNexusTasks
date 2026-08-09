package com.example.recipeapp.presentation.screens.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileContract.ProfileState())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<ProfileContract.ProfileEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: ProfileContract.ProfileIntent) {
        when (intent) {
            is ProfileContract.ProfileIntent.OnNotificationsChanged -> {
                _uiState.update { it.copy(notificationsEnabled = intent.isEnabled) }
            }

            is ProfileContract.ProfileIntent.OnLocationChanged -> {
                _uiState.update { it.copy(locationEnabled = intent.isEnabled) }
            }

        }
    }
}
