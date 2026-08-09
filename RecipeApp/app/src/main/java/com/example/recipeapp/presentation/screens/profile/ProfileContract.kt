package com.example.recipeapp.presentation.screens.profile

object ProfileContract {

    data class ProfileState(
        val notificationsEnabled: Boolean = false,
        val locationEnabled: Boolean = false
    )

    sealed interface ProfileIntent {
        data class OnNotificationsChanged(
            val isEnabled: Boolean
        ) : ProfileIntent

        data class OnLocationChanged(
            val isEnabled: Boolean
        ) : ProfileIntent


    }

    sealed interface ProfileEffect {
        data class ShowMessage(val message: String) : ProfileEffect
    }
}
