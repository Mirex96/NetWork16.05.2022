package com.example.network16052022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible


const val KEY_ACTIVITY = "KEY_ACTIVITY"
const val KEY_TIME = "KEY_TIME"


class MainActivity : AppCompatActivity(), StoreContract.View {
    private lateinit var text: TextView
    private lateinit var progressBar: View
    private lateinit var lastRequestGroup: View
    private lateinit var successfulRequestsGroup: View
    private lateinit var requestGroup: View
    private lateinit var buttonLastRequest: Button
    private lateinit var buttonSuccessfulRequests: Button
    private lateinit var buttonRequest: Button
    private lateinit var textLastActivityRequest: TextView
    private lateinit var error: TextView

    private val presenter: StoreContract.Presenter by lazy {
        StorePresenter.create(StoreRepository.create())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.text)
        progressBar = findViewById(R.id.progress)
        lastRequestGroup = findViewById(R.id.lastRequestGroup)
        successfulRequestsGroup = findViewById(R.id.successfulRequestsGroup)
        requestGroup = findViewById(R.id.requestGroup)
        buttonLastRequest = findViewById(R.id.buttonLastRequest)
        buttonSuccessfulRequests = findViewById(R.id.buttonSuccessfulRequests)
        buttonRequest = findViewById(R.id.buttonRequest)
        textLastActivityRequest = findViewById(R.id.textLastActivityRequest)
        error = findViewById(R.id.error)

        presenter.onAttach(this)
        presenter.onLoad()

        buttonLastRequest.setOnClickListener {
            presenter.onLastRequest(activity = String(), time = String())
        }

        buttonSuccessfulRequests.setOnClickListener {
            presenter.onSuccessfulRequests()
        }

        buttonRequest.setOnClickListener {
            presenter.onLoad()
        }


    }

    override fun showProgress() {
        progressBar.isVisible = true
    }

    override fun hideProgress() {
        progressBar.isVisible = false

    }

    override fun showContent() {
        findViewById<View>(R.id.contentGroup).isVisible = true
    }

    override fun hideContent() {
        findViewById<View>(R.id.contentGroup).isVisible = false

    }

    override fun showLastActivityRequest(activity: String, time: String) {
        textLastActivityRequest.text = "$activity\n$time"
    }

    override fun showStartActivityLastRequest(activity: String, time: String) {
        val intent = Intent(this, ActivityLastRequest::class.java)
        intent.putExtra(KEY_ACTIVITY, activity)
        intent.putExtra(KEY_TIME, time)
        startActivity(intent)
    }

    override fun showStartActivitySuccessfulRequests(activity: List<String>) {
        val intent = Intent(this, ActivitySuccessfulRequests::class.java)
        intent.putExtra(KEY_ACTIVITY, activity.toString())
        startActivity(intent)
        Log.d("ACTIVITY", activity.toString())
    }

    override fun showError(reason: String) {
        error.isVisible = true
        error.text = reason

    }

    override fun hideError() {
        error.isVisible = false

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}