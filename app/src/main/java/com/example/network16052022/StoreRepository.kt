package com.example.network16052022

import android.app.Activity

class StoreRepository private constructor() : StoreContract.Repository {


    override fun onLoad(onSuccess: (Activity) -> Unit, onError: (String) -> Unit) {
        //        try {
//            val response = NetworkModule.api.getActivity().execute()
//
//            if (response.isSuccessful) {
//                onSuccess(response.body())
//            } else {
//                onError(response.errorBody().toString())
//            }
//        } catch (e: Throwable) {
//            onError(e.message.toString())
//        }
    }

    companion object {
        fun create() = StoreRepository()
    }
}