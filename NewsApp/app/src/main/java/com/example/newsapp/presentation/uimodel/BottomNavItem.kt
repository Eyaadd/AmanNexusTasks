package com.example.week2amantasksxml.models

import androidx.annotation.DrawableRes
import com.example.newsapp.presentation.navigation.Screen

data class BottomNavItem(
    val route: Screen,
    val title: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
)
