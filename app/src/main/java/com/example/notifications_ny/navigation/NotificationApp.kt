package com.example.notifications_ny.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun NotificationApp(navController: NavHostController = rememberNavController()) {
    NotificationNavHost(navController = navController)
}