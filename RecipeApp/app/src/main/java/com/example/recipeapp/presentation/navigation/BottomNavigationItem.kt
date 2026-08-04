package com.example.recipeapp.presentation.navigation

import androidx.annotation.DrawableRes
import com.example.recipeapp.R

data class BottomNavigationItem(
    val route: AppRoute,
    val label: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        route = AppRoute.Home,
        label = "Home",
        selectedIcon = R.drawable.ic_home_selected,
        unselectedIcon = R.drawable.ic_home
    ),
    BottomNavigationItem(
        route = AppRoute.Search,
        label = "Search",
        selectedIcon = R.drawable.ic_search_selected,
        unselectedIcon = R.drawable.ic_search
    ),
    BottomNavigationItem(
        route = AppRoute.Favorites,
        label = "Favorites",
        selectedIcon = R.drawable.ic_favorite_checked,
        unselectedIcon = R.drawable.ic_favorite
    ),
    BottomNavigationItem(
        route = AppRoute.Profile,
        label = "Profile",
        selectedIcon = R.drawable.ic_profile_selected,
        unselectedIcon = R.drawable.ic_profile
    )
)