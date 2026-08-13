package com.example.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp.presentation.screen.home.HomeScreen
import com.example.newsapp.presentation.screen.login.LoginScreen
import com.example.newsapp.presentation.screen.search.SearchScreen

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
                onNavigateToSearch = {
                    navController.navigate(Screen.Search) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<Screen.Search> {
            SearchScreen(

            ){
                navController.popBackStack()
            }
        }
    }
}