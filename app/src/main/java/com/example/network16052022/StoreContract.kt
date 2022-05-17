package com.example.network16052022

import android.app.Activity

interface StoreContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showContent()
        fun hideContent()
        fun showStartActivityLastRequest()
        fun showStartActivitySuccessfulRequests()
    }

    interface Presenter {
        fun onAttach(view: View)
        fun onDetach()
        fun onLoad()
        fun onLastRequest()
        fun onSuccessfulRequests()
    }

    interface Repository {
        fun onLoad(onSuccess: (Activity) -> Unit, onError: (String) -> Unit)

    }
}