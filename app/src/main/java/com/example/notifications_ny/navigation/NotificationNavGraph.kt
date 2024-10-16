package com.example.notifications_ny.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.notifications_ny.screens.DetailsScreen
import com.example.notifications_ny.screens.DetailsScreenDestination
import com.example.notifications_ny.screens.MainScreen
import com.example.notifications_ny.screens.MainScreenDestination

const val MY_URI = "https://example.com"

@Composable
fun NotificationNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainScreenDestination.route
    ) {
        composable(route = MainScreenDestination.route) {
            MainScreen { msg ->
                navController.navigate("${DetailsScreenDestination.route}/$msg")
            }
        }

        composable(
            route = "${DetailsScreenDestination.route}/{msg}",
            arguments = listOf(navArgument(name = "msg") { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink { uriPattern = "$MY_URI/msg={msg}" })
        ) {
            val textMsg = remember { it.arguments?.getString("msg") }
            DetailsScreen(textMsg = textMsg)
        }
    }
}