package com.example.network16052022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView



class ActivityLastRequest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        val textLastRequestNameResult = findViewById<TextView>(R.id.textLastRequestNameResult)
        val textLastRequestTimeResult = findViewById<TextView>(R.id.textLastRequestTimeResult)
        val buttonLastRequestResult = findViewById<Button>(R.id.buttonLastRequestResult)

        val activity = intent.putExtra(KEY_ACTIVITY, String())
        val time =  intent.putExtra(KEY_TIME, String())

        textLastRequestNameResult.text = activity.toString()
        textLastRequestTimeResult.text = time.toString()



        buttonLastRequestResult.setOnClickListener {
            finish()
        }
    }
}