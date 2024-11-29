package com.example.homenest.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.homenest.R
import com.example.homenest.models.TabBarItem
import com.example.homenest.screens.Bookmark
import com.example.homenest.screens.Home
import com.example.homenest.screens.Notification
import com.example.homenest.screens.Profile

@Composable
fun AppBottomTab(navControllerApp: NavController){
val navControllerTab = rememberNavController()
    val list = listOf(
        TabBarItem(
            title = "Home",
            selectedIcon = R.drawable.icon_home_select,
            unselectedIcon = R.drawable.icon_home
        ),
        TabBarItem(
            title = "Bookmark",
            selectedIcon = R.drawable.icon_marker_select,
            unselectedIcon = R.drawable.icon_marker
        ),
        TabBarItem(
            title = "Notification",
            selectedIcon = R.drawable.icon_bell_select,
            unselectedIcon = R.drawable.icon_bell
        ),
        TabBarItem(
            title = "Profile",
            selectedIcon = R.drawable.icon_person_select,
            unselectedIcon = R.drawable.icon_person
        ),
    )

    Scaffold (bottomBar = { MyBottomTab(tabBarItems = list, navControllerTab ) }) {
        NavHost(
            navController = navControllerTab,
            startDestination = "Home",
            modifier = Modifier.padding(it)
        ) {
            composable("Home") {
                Home(navControllerApp) }
            composable("Bookmark") {
                Bookmark()
            }
            composable("Notification") {
                Notification()
            }
            composable("Profile") {
                Profile(navControllerApp)
            }
        }
    }
}