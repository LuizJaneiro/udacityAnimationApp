package com.udacity.utils.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class DownloadStatus: Parcelable {
    SUCCESS, FAILED
}