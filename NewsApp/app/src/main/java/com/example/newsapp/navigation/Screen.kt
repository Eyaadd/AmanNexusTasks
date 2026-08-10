package com.example.newsapp.navigation


import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object Login : Screen

    @Serializable
    data object Home : Screen



}