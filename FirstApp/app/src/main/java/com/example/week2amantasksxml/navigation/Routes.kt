package com.example.week2amantasksxml.navigation

sealed class Screen(val route: String) {
    object Login : Screen("Login")
    object Settings : Screen("Settings")
}