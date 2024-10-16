package com.example.notifications_ny.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notifications_ny.NotificationViewModel
import com.example.notifications_ny.R
import com.example.notifications_ny.navigation.NavigationDestination

object MainScreenDestination : NavigationDestination {
    override val titleRes = R.string.main
    override val route = "main"
}

@Composable
fun MainScreen(
    notificationViewModel: NotificationViewModel = hiltViewModel(),
    onNavigateToDetailsScreen: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = notificationViewModel::createNotification) {
            Text(text = "Simple Notification")
        }
        Button(onClick = notificationViewModel::updateNotification) {
            Text(text = "Update Notification")
        }
        Button(onClick = notificationViewModel::cancelNotification) {
            Text(text = "Cancel Notification")
        }
        Button(onClick = { onNavigateToDetailsScreen("Coming from Main Screen") }) {
            Text(text = "Navigate to Example Screen")
        }
        Button(onClick = notificationViewModel::showProgressNotification) {
            Text(text = "Show Progress Notification")
        }
    }
}
