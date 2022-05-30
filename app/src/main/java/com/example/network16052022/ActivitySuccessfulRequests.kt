package com.example.network16052022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.network16052022.network.NetworkModule
import kotlin.concurrent.thread

class ActivitySuccessfulRequests : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_successful_requests)

        val textSuccessfulRequestsNameResult =
            findViewById<TextView>(R.id.textSuccessfulRequestsNameResult)
        val textSuccessfulRequestsTimeResult =
            findViewById<TextView>(R.id.textSuccessfulRequestsTimeResult)
        val buttonSuccessfulRequestsResult =
            findViewById<Button>(R.id.buttonSuccessfulRequestsResult)


        val activity = intent.putExtra(KEY_ACTIVITY, String())
        textSuccessfulRequestsNameResult.text = activity.toString()








        buttonSuccessfulRequestsResult.setOnClickListener {
            finish()
        }
    }
}