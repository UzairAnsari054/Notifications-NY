package com.example.notifications_ny

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.example.notifications_ny.navigation.MY_URI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Singleton
    @Provides
    fun provideNotificationManager(@ApplicationContext context: Context): NotificationManagerCompat {
        val notificationManager = NotificationManagerCompat.from(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification = NotificationChannel(
                "notification_channel_id",
                "Notification Channel Name",
                NotificationManager.IMPORTANCE_HIGH
            )

            val progressNotification = NotificationChannel(
                "progress_notification_id",
                "Progress Notification Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(notification)
            notificationManager.createNotificationChannel(progressNotification)
        }
        return notificationManager
    }

    @MainNotificationCompatBuilder
    @Singleton
    @Provides
    fun provideNotificationBuilder(@ApplicationContext context: Context): NotificationCompat.Builder {

        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            PendingIntent.FLAG_IMMUTABLE
        } else 0

        val notificationClickedIntent = Intent(context, MainActivity::class.java)
        val notificationClickedPendingIntent = PendingIntent.getActivity(
            context, 1, notificationClickedIntent, flag
        )

        val notificationToastActionIntent = Intent(context, MyReceiver::class.java).also {
            it.putExtra("message", "This is a yummy toast!!")
        }
        val notificationToastActionPendingIntent = PendingIntent.getBroadcast(
            context, 2, notificationToastActionIntent, flag
        )

        val notificationDeeplinkNavigateIntent = Intent(
            Intent.ACTION_VIEW,
            "$MY_URI/msg=Coming from notification".toUri(),
            context,
            MainActivity::class.java
        )
        val notificationDeeplinkNavigatePendingIntent: PendingIntent = TaskStackBuilder.create(context).run {
                addNextIntentWithParentStack(notificationDeeplinkNavigateIntent)
                getPendingIntent(3, flag)
            }

        return NotificationCompat
            .Builder(context, "notification_channel_id")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Simple Notification")
            .setContentText("This is a description")
            .setStyle(NotificationCompat.BigTextStyle())
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//            .setContentIntent(notificationClickedPendingIntent)
            .setContentIntent(notificationDeeplinkNavigatePendingIntent)
            .addAction(0, "Show Toast", notificationToastActionPendingIntent)
    }

    @ProgressNotificationCompatBuilder
    @Provides
    @Singleton
    fun provideProgressNotificationBuilder(@ApplicationContext context: Context): NotificationCompat.Builder {
        return NotificationCompat
            .Builder(context, "progress_notification_id")
            .setContentTitle("Downloading")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainNotificationCompatBuilder


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProgressNotificationCompatBuilder