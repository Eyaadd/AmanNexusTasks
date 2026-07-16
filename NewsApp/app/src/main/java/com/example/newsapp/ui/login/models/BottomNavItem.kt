package com.example.week2amantasksxml.models

import androidx.annotation.DrawableRes

data class BottomNavItem(
    val route: String,
    val title: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
)
