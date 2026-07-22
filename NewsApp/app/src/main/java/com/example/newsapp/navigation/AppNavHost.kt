package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp.ui.home.screens.HomeScreen
import com.example.newsapp.ui.login.screens.LoginScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login,
        modifier = modifier
    ) {
        composable<Screen.Login> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home) {
                        popUpTo<Screen.Login> {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }
            )
        }


        composable<Screen.Home> {
            HomeScreen(

            )
        }

    }
}