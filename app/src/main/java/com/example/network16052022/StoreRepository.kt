package com.example.network16052022


import android.util.Log
import com.example.network16052022.network.Activity
import com.example.network16052022.network.NetworkModule

class StoreRepository private constructor() : StoreContract.Repository {


    private val successfulRequests = mutableListOf<Activity>()


    override fun onLoad(onSuccess: (Activity) -> Unit, onError: (String) -> Unit) {
        try {
            val response = NetworkModule.api.getActivity().execute()

            if (response.isSuccessful) {
                onSuccess(response.body()!!)
                successfulRequests.add(response.body()!!)
                Log.d("CHECK", successfulRequests.toString())
            } else {
                onError(response.errorBody().toString())
            }
        } catch (e: Throwable) {
            onError(e.toString())
        }
    }

    override fun getStorageData(): List<Activity> {
        return successfulRequests
    }

    companion object {
        fun create() = StoreRepository()
    }
}