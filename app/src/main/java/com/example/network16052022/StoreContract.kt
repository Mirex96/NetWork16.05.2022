package com.example.network16052022

import com.example.network16052022.network.Activity


interface StoreContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showContent()
        fun hideContent()

        fun showLastActivityRequest(activity: String, time: String)

        // Нужно указать Activity
        fun showStartActivityLastRequest(activity: String, time: String)

        // Нужно указать
        fun showStartActivitySuccessfulRequests(activity: List<String>)

        fun showError(reason: String)
        fun hideError()
    }

    interface Presenter {
        fun onAttach(view: View)
        fun onDetach()
        fun onLoad()
        fun onLastRequest(activity: String, time: String)
        fun onSuccessfulRequests()

        data class State(
            val activity: Activity,
            val time: String
        )
    }

    interface Repository {
        fun onLoad(onSuccess: (Activity) -> Unit, onError: (String) -> Unit)
        fun getStorageData(): List<Activity>

    }
}