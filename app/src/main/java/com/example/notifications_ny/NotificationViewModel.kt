package com.example.notifications_ny

import android.annotation.SuppressLint
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MissingPermission")
@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationManager: NotificationManagerCompat,
    @MainNotificationCompatBuilder
    private val notificationBuilder: NotificationCompat.Builder,
    @ProgressNotificationCompatBuilder
    private val progressNotificationBuilder: NotificationCompat.Builder
) : ViewModel() {

    fun createNotification() {
        notificationManager.notify(0, notificationBuilder.build())
    }

    fun updateNotification() {
        notificationManager.notify(0, notificationBuilder.setContentText("This one is now updated").build())
    }

    fun cancelNotification() {
        notificationManager.cancel(0)
    }

    fun showProgressNotification() {
        val max = 10
        var progress = 0
        viewModelScope.launch {
            while (progress != max) {
                delay(1000)
                progress += 1
                notificationManager.notify(
                    4, progressNotificationBuilder
                        .setContentText("$progress/$max")
                        .setProgress(max, progress, false)
                        .build()
                )
            }
            notificationManager.notify(
                4, progressNotificationBuilder
                    .setContentTitle("Completed")
                    .setContentText("")
                    .setContentIntent(null)
                    .setProgress(0, 0, false)
                    .clearActions()
                    .build()
            )
        }
    }


}