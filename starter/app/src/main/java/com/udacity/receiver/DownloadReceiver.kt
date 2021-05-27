package com.udacity.receiver

import android.app.DownloadManager
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.udacity.utils.model.DownloadStatus
import com.udacity.utils.model.NotificationModel
import com.udacity.utils.sendNotification

class DownloadReceiver: BroadcastReceiver() {

    private var downloadId: Long = 0

    override fun onReceive(context: Context, intent: Intent) {
        val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)

        if(id == downloadId) {
            val query = DownloadManager.Query()
            query.setFilterById(id)

            val downloadManager = ContextCompat.getSystemService(
                    context,
                    DownloadManager::class.java
            ) as DownloadManager
            val cursor = downloadManager.query(query)

            if (cursor.moveToFirst()) {
                when (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
                    DownloadManager.STATUS_SUCCESSFUL -> {
                        sendNotification(context, cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE)), DownloadStatus.SUCCESS)
                    }
                    DownloadManager.STATUS_FAILED -> {
                        sendNotification(context, cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE)), DownloadStatus.FAILED)
                    }
                }
            }
        }
    }

    fun setDownloadId(id: Long) {
        this.downloadId = id
    }

    private fun sendNotification(context: Context, fileName: String, downloadStatus: DownloadStatus) {
        val notificationManager = ContextCompat.getSystemService(
                context,
                NotificationManager::class.java
        ) as NotificationManager
        notificationManager.sendNotification(
            NotificationModel(
                    fileName = fileName,
                    downloadStatus = downloadStatus
            ),
            context
        )
    }
}