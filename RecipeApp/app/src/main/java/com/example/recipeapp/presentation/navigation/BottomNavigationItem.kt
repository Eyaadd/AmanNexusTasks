package com.example.recipeapp.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.recipeapp.R

data class BottomNavigationItem(
    val route: AppRoute,
    @param:StringRes val labelRes: Int,
    @param:DrawableRes val selectedIcon: Int,
    @param:DrawableRes val unselectedIcon: Int
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        route = AppRoute.Home,
        labelRes = R.string.nav_home,
        selectedIcon = R.drawable.ic_home_selected,
        unselectedIcon = R.drawable.ic_home
    ),
    BottomNavigationItem(
        route = AppRoute.Search,
        labelRes = R.string.nav_search,
        selectedIcon = R.drawable.ic_search_selected,
        unselectedIcon = R.drawable.ic_search
    ),
    BottomNavigationItem(
        route = AppRoute.Favorites,
        labelRes = R.string.nav_favorites,
        selectedIcon = R.drawable.ic_favorite_checked,
        unselectedIcon = R.drawable.ic_favorite
    ),
    BottomNavigationItem(
        route = AppRoute.Profile,
        labelRes = R.string.nav_profile,
        selectedIcon = R.drawable.ic_profile_selected,
        unselectedIcon = R.drawable.ic_profile
    )
)
