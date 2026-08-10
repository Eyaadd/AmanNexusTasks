package com.example.recipeapp.presentation.screens.profile

object ProfileContract {

    data class ProfileState(
        val notificationsEnabled: Boolean = false,
        val locationEnabled: Boolean = false,
        val showLocationSettingsDialog: Boolean = false,
        val showNotificationSettingsDialog: Boolean = false,
        val showLanguageDialog: Boolean = false,
        val showDisplayModeDialog: Boolean = false

    )

    sealed interface ProfileIntent {
        data class OnNotificationsChanged(
            val isEnabled: Boolean
        ) : ProfileIntent

        data class OnLocationChanged(
            val isEnabled: Boolean
        ) : ProfileIntent

        data class ShowNotificationSettingsDialog(
            val isShown: Boolean
        ) : ProfileIntent

        data class ShowLocationSettingsDialog(
            val isShown: Boolean
        ) : ProfileIntent

        data class ShowLanguageDialog(
            val isShown: Boolean
        ) : ProfileIntent

        data class OnLanguageSelected(
            val languageTag: String
        ) : ProfileIntent

        data class ShowDisplayModeDialog(
            val isShown: Boolean
        ) : ProfileIntent

        data class OnDisplayModeSelected(
            val mode: String
        ) : ProfileIntent

    }

    sealed interface ProfileEffect {
        data class ShowMessage(val message: String) : ProfileEffect
        data class ChangeLanguage(val languageTag: String) : ProfileEffect
        data class ChangeDisplayMode(val mode: String) : ProfileEffect
    }
}
