package com.example.recipeapp.presentation.navigation


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.recipeapp.presentation.screens.details.DetailsScreen
import com.example.recipeapp.presentation.screens.favorites.FavoritesScreen
import com.example.recipeapp.presentation.screens.home.HomeScreen

@Composable
fun RecipeAppNavHost() {
    val bottomBarRoutes = setOf(
        AppRoute.Home::class,
        AppRoute.Search::class,
        AppRoute.Favorites::class,
        AppRoute.Profile::class
    )
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    val showBottomBar =
        bottomBarRoutes.any{ route ->
            currentDestination?.hasRoute(route) == true
        }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                RecipeBottomNavigationBar(
                    currentDestination = currentDestination,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(
                                navController.graph
                                    .findStartDestination()
                                    .id
                            ) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = AppRoute.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<AppRoute.Home> {
                HomeScreen(
                    onNavigateToDetails = { recipeId ->
                        navController.navigate(
                            AppRoute.RecipeDetails(
                                recipeId = recipeId
                            )
                        )
                    },
                    onNavigateToSearch = {
                        navController.navigate(AppRoute.Search)
                    }
                )
            }

            composable<AppRoute.Search> {
            }

            composable<AppRoute.Favorites> {
                FavoritesScreen(
                    onNavigateToDetails = { recipeId ->
                        navController.navigate(
                            AppRoute.RecipeDetails(
                                recipeId = recipeId
                            )
                        )

                    }
                )
            }

            composable<AppRoute.Profile> {
            }

            composable<AppRoute.RecipeDetails> { backStackEntry ->
                val route =
                    backStackEntry.toRoute<AppRoute.RecipeDetails>()

                DetailsScreen(
                    recipeId = route.recipeId,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

