package com.udacity.ui

import android.app.DownloadManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.udacity.R
import com.udacity.receiver.DownloadReceiver
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    private val downloadReceiver = DownloadReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        registerReceiver(downloadReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

        custom_button.setOnClickListener {
            download("TESTE", "TESTE DESCRICAO", "https://github.com/bumptech/glide/archive/refs/heads/master.zip")
        }
    }

    private fun download(title: String, description: String, url: String) {
        val request =
            DownloadManager.Request(Uri.parse(url))
                .setTitle(title)
                .setDescription(description)
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)

        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val downloadID =
            downloadManager.enqueue(request)// enqueue puts the download request in the queue.
        downloadReceiver.setDownloadId(downloadID)
    }

}
