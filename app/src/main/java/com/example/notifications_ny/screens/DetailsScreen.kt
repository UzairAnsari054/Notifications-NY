package com.example.notifications_ny.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.notifications_ny.R
import com.example.notifications_ny.navigation.NavigationDestination

object DetailsScreenDestination: NavigationDestination {
    override val titleRes = R.string.details
    override val route = "details"

}

@Composable
fun DetailsScreen(
    textMsg: String?
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!textMsg.isNullOrBlank()){
            Text(text = textMsg)
        }
    }
}