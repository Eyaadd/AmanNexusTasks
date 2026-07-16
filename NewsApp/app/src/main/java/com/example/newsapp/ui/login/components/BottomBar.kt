package com.example.newsapp.ui.login.components

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.week2amantasksxml.models.BottomNavItem


val bottomNavItems = listOf(
    BottomNavItem(
        route = "home",
        title = "Home",
        selectedIcon = R.drawable.ic_home_active,
        unselectedIcon = R.drawable.ic_home_inactive
    ),
    BottomNavItem(
        route = "news",
        title = "News",
        selectedIcon = R.drawable.ic_news_active,
        unselectedIcon = R.drawable.ic_news_inactive
    ),
    BottomNavItem(
        route = "favorites",
        title = "Favorites",
        selectedIcon = R.drawable.ic_saved_active,
        unselectedIcon = R.drawable.ic_saved_inactive
    ),
    BottomNavItem(
        route = "profile",
        title = "Profile",
        selectedIcon = R.drawable.ic_profile_active,
        unselectedIcon = R.drawable.ic_profile_inactive
    )
)

@Composable
fun BottomBar(
    currentRoute: String,
    onItemClick: (String) -> Unit
) {
    NavigationBar {
        bottomNavItems.forEach { item ->

            val selected = currentRoute == item.route
            Log.d("BottomBar", "${item.route} selected = $selected")

            NavigationBarItem(
                selected = selected,
                onClick = {
                    onItemClick(item.route)
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(
                            if (selected)
                                item.selectedIcon
                            else
                                item.unselectedIcon
                        ),
                        tint = Color.Unspecified,
                        contentDescription = item.title,
                    )
                },
                label = {
                    Text(item.title)
                }
            )
        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar(
        currentRoute = "Home"
    ) { }
}