package com.udacity.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.udacity.R
import com.udacity.utils.NOTIFICATION_MODEL_KEY
import com.udacity.utils.model.NotificationModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        val extras = intent.extras

        Log.d("TESTE", extras.toString())
        extras?.let {
            val notificationModel = it.getParcelable<NotificationModel>(NOTIFICATION_MODEL_KEY)
            val tv = findViewById<AppCompatTextView>(R.id.tvFileName)
            tv.text = notificationModel?.fileName
        }
    }

}
