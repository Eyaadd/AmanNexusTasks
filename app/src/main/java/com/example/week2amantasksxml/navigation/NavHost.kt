package com.example.week2amantasksxml.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.week2amantasksxml.compose.LoginScreenContent
import com.example.week2amantasksxml.compose.SettingsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreenContent(
                onSignInClicked = {
                    navController.navigate(Screen.Settings.route)
                },
                modifier = modifier
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                modifier = modifier,
                onClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}