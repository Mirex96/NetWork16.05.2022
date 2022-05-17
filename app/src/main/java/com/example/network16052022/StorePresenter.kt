package com.example.network16052022

import android.os.Handler
import android.os.Looper

class StorePresenter private constructor(
    private val repository: StoreContract.Repository
) : StoreContract.Presenter {
    private var view: StoreContract.View? = null

    override fun onAttach(view: StoreContract.View) {
        this.view = view
    }

    override fun onDetach() {
        view = null
    }

    override fun onLoad() {
        view?.showProgress()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                view?.showContent()
                view?.hideProgress()
            }, 3_000L

        )
    }

    override fun onLastRequest() {
        view?.showProgress()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                view?.showStartActivityLastRequest()
                view?.hideProgress()

            },
            2_000L,
        )


    }

    override fun onSuccessfulRequests() {
        view?.showProgress()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                view?.showStartActivitySuccessfulRequests()
                view?.hideProgress()
            }, 2_000L

        )
    }

    companion object {
        fun create(
            repository: StoreContract.Repository
        ) = StorePresenter(repository)
    }

}