package com.example.newsapp.navigation


sealed class Screen(
    val route: String
) {

    data object Login : Screen("login")

    data object Home : Screen("home")
}