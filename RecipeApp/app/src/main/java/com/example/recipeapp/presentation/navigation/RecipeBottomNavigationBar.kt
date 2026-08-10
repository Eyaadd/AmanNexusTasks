package com.example.recipeapp.presentation.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.example.recipeapp.presentation.theme.RecipeYellow

@Composable
fun RecipeBottomNavigationBar(
    currentDestination: NavDestination?,
    onNavigate: (AppRoute) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        bottomNavigationItems.forEach { item ->

            val isSelected = when (item.route) {
                AppRoute.Home ->
                    currentDestination?.hasRoute<AppRoute.Home>() == true

                AppRoute.Search ->
                    currentDestination?.hasRoute<AppRoute.Search>() == true

                AppRoute.Favorites ->
                    currentDestination?.hasRoute<AppRoute.Favorites>() == true

                AppRoute.Profile ->
                    currentDestination?.hasRoute<AppRoute.Profile>() == true

                else -> false
            }

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    onNavigate(item.route)
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(
                            if (isSelected) {
                                item.selectedIcon
                            } else {
                                item.unselectedIcon
                            }
                        ),
                        contentDescription = stringResource(item.labelRes),
                        tint = Color.Unspecified
                    )
                },
                label = {
                    Text(stringResource(item.labelRes))
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = RecipeYellow,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = RecipeYellow.copy(alpha = 0.12f)
                )
            )
        }
    }
}
