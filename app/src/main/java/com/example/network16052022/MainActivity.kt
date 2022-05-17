package com.example.network16052022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity(), StoreContract.View {
    private lateinit var text: TextView
    private lateinit var floatingAdd: View
    private lateinit var progressBar: View
    private lateinit var lastRequestGroup: View
    private lateinit var successfulRequestsGroup: View
    private lateinit var requestGroup: View
    private lateinit var buttonLastRequest: Button
    private lateinit var buttonSuccessfulRequests: Button

    private val presenter: StoreContract.Presenter by lazy {
        StorePresenter.create(StoreRepository.create())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.text)
        floatingAdd = findViewById(R.id.floatingAdd)
        progressBar = findViewById(R.id.progress)
        lastRequestGroup = findViewById(R.id.lastRequestGroup)
        successfulRequestsGroup = findViewById(R.id.successfulRequestsGroup)
        requestGroup = findViewById(R.id.requestGroup)
        buttonLastRequest = findViewById(R.id.buttonLastRequest)
        buttonSuccessfulRequests = findViewById(R.id.buttonSuccessfulRequests)

        presenter.onAttach(this)
        presenter.onLoad()

        buttonLastRequest.setOnClickListener {
            presenter.onLastRequest()
        }

        buttonSuccessfulRequests.setOnClickListener {
            presenter.onSuccessfulRequests()
        }


    }

    override fun showProgress() {
        progressBar.isVisible = true
    }

    override fun hideProgress() {
        progressBar.isVisible = false

    }

    override fun showContent() {
        findViewById<View>(R.id.lastRequestGroup).isVisible = true
        findViewById<View>(R.id.successfulRequestsGroup).isVisible = true
        findViewById<View>(R.id.requestGroup).isVisible = true
        floatingAdd.isVisible = true
        text.isVisible = true
    }

    override fun hideContent() {
        findViewById<View>(R.id.lastRequestGroup).isVisible = false
        findViewById<View>(R.id.successfulRequestsGroup).isVisible = false
        findViewById<View>(R.id.requestGroup).isVisible = false
        floatingAdd.isVisible = false
        text.isVisible = false
    }

    override fun showStartActivityLastRequest() {
        val intent = Intent(this, ActivityLastRequest::class.java)
        startActivity(intent)
    }

    override fun showStartActivitySuccessfulRequests() {
        val intent = Intent(this, ActivitySuccessfulRequests::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}