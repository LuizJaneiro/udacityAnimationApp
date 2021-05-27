package com.udacity.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import com.udacity.R
import com.udacity.ui.DetailActivity
import com.udacity.ui.MainActivity
import com.udacity.utils.model.NotificationModel

private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

fun NotificationManager.sendNotification(notificationModel: NotificationModel, applicationContext: Context) {

    val contentMainIntent = Intent(applicationContext, MainActivity::class.java)
    val contentMainPendingIntent = PendingIntent.getActivity(
            applicationContext,
            NOTIFICATION_ID,
            contentMainIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
    )

    val contentIntent = Intent(applicationContext, DetailActivity::class.java)
    contentIntent.putExtra(NOTIFICATION_MODEL_KEY, notificationModel)
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.notification_channel_id)
    )
            .setSmallIcon(R.drawable.ic_assistant_black_24dp)
            .setContentTitle(applicationContext.getString(R.string.notification_title))
            .setContentText(applicationContext.getString(R.string.notification_description))
            .setContentIntent(contentMainPendingIntent)
            .setAutoCancel(true)
            .addAction(
                    R.drawable.ic_assistant_black_24dp,
                    applicationContext.getString(R.string.notification_button),
                    contentPendingIntent
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)

    notify(NOTIFICATION_ID, builder.build())
}