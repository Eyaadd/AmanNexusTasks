package com.example.recipeapp.presentation.screens.profile.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SettingItem(
    @param:StringRes val labelRes: Int,
    @param:DrawableRes val iconRes: Int,
    val isChecked: Boolean? = null,
    val onCheckedChange: ((Boolean) -> Unit)? = null,
    val onClick: () -> Unit = {}
)
