package com.udacity.utils.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NotificationModel(
    val fileName: String,
    val downloadStatus: DownloadStatus
): Parcelable