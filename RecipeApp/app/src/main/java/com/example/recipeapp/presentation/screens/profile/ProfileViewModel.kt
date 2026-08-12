package com.example.recipeapp.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.recipeapp.domain.usecase.LogoutUseCase

class ProfileViewModel(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

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

            is ProfileContract.ProfileIntent.ShowNotificationSettingsDialog -> _uiState.update {
                it.copy(showNotificationSettingsDialog = intent.isShown)
            }

            is ProfileContract.ProfileIntent.ShowLocationSettingsDialog -> _uiState.update {
                it.copy(showLocationSettingsDialog = intent.isShown)
            }

            is ProfileContract.ProfileIntent.ShowLanguageDialog -> _uiState.update {
                it.copy(showLanguageDialog = intent.isShown)
            }

            is ProfileContract.ProfileIntent.OnLanguageSelected -> {
                _uiState.update { it.copy(showLanguageDialog = false) }
                viewModelScope.launch {
                    _effect.send(
                        ProfileContract.ProfileEffect.ChangeLanguage(intent.languageTag)
                    )
                }
            }

            is ProfileContract.ProfileIntent.ShowDisplayModeDialog -> _uiState.update {
                it.copy(showDisplayModeDialog = intent.isShown)
            }

            is ProfileContract.ProfileIntent.OnDisplayModeSelected -> {
                _uiState.update { it.copy(showDisplayModeDialog = false) }
                viewModelScope.launch {
                    _effect.send(
                        ProfileContract.ProfileEffect.ChangeDisplayMode(intent.mode)
                    )
                }
            }

            ProfileContract.ProfileIntent.OnLogoutClicked -> {
                logoutUseCase()
                viewModelScope.launch {
                    _effect.send(ProfileContract.ProfileEffect.NavigateToLogin)
                }
            }
        }
    }
}
