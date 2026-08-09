package com.example.recipeapp.presentation.screens.profile.components

import androidx.annotation.DrawableRes

data class SettingItem(
    val label: String,
    @param:DrawableRes val iconRes: Int,
    val isChecked: Boolean? = null,
    val onCheckedChange: ((Boolean) -> Unit)? = null,
    val onClick: () -> Unit = {}
)
