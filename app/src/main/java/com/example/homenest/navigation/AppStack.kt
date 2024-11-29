package com.example.homenest.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.homenest.components.Login
import com.example.homenest.components.Register
import com.example.homenest.components.Welcome
import com.example.homenest.components.item.ProductDetail


@Composable
fun AppStack() {
    val navControllerApp = rememberNavController()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_Save", Context.MODE_PRIVATE)
    val email = sharedPreferences.getString("email", null)
    val password = sharedPreferences.getString("pass", null)

    val startDestination =  "welcome"


    NavHost(navControllerApp, startDestination = startDestination) {
        composable("welcome") { Welcome(navControllerApp) }
        composable("login") { Login(navControllerApp) }
        composable("register") { Register(navControllerApp) }
        composable("appBottomTab") { AppBottomTab(navControllerApp) }

        composable(
            route = "productDetail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            if (productId != null) {
                ProductDetail(navControllerApp, productId)
            }
        }
    }
}