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

class ActivityLastRequest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        val textLastRequestNameResult = findViewById<TextView>(R.id.textLastRequestNameResult)
        val textLastRequestTimeResult = findViewById<TextView>(R.id.textLastRequestTimeResult)
        val buttonLastRequestResult = findViewById<Button>(R.id.buttonLastRequestResult)

        val handler = Handler(Looper.getMainLooper())

        thread {
            try {
                val activity = NetworkModule.api.getActivity()
                val response = activity.execute()

                if (response.isSuccessful) {
                    val body = response.body()
                    val activityParam = body?.activity ?: ""
                    val typeParam = body?.type ?: ""

                    handler.post {
                        textLastRequestNameResult.text = activityParam
                        textLastRequestTimeResult.text = typeParam
                    }

                } else {
                    Log.d("VB", "ERROR")
                }
            } catch (e: Throwable) {
                Log.d("VB", e.toString())

            }

        }



        buttonLastRequestResult.setOnClickListener {
            finish()
        }
    }
}