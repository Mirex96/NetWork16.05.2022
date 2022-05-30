package com.example.network16052022

import android.os.Handler
import android.os.Looper
import com.example.network16052022.StoreContract.*
import com.example.network16052022.StoreContract.Presenter.*
import com.example.network16052022.utils.ui
import java.time.OffsetDateTime
import kotlin.concurrent.thread

class StorePresenter private constructor(
    private val repository: Repository
) : Presenter {
    private var view: View? = null

    private lateinit var state: State


    override fun onAttach(view: View) {
        this.view = view
    }

    override fun onDetach() {
        view = null
    }

    override fun onLoad() {
        view?.showProgress()
        view?.hideError()
        thread {
            repository.onLoad({
                ui {
                    view?.showContent()
                    view?.hideProgress()
                    val time = OffsetDateTime.now().toString()
                    view?.showLastActivityRequest(activity = it.activity, time)

                    state = State(it, time)
                }
            }, {
                // Отображение ошибки
                ui {
                    view?.showContent()
                    view?.showError(it)       // it - передаем строку
                    view?.hideProgress()
                }
            })
        }
    }

    override fun onLastRequest(activity: String, time: String) {
        state.let {
            view?.showStartActivityLastRequest(it.activity.activity, it.time)

        }


    }

    override fun onSuccessfulRequests() {
        view?.showStartActivitySuccessfulRequests(
            repository.getStorageData().map { it -> it.activity }
        )
    }

    companion object {
        fun create(
            repository: Repository
        ) = StorePresenter(repository)
    }

}