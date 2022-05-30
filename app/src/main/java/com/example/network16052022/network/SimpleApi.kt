package com.example.network16052022.network


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import retrofit2.Call
import retrofit2.http.GET

interface SimpleApi {
    @GET("activity")
    fun getActivity(): Call<Activity>
}


@JsonClass(generateAdapter = true)
data class Activity(
    @Json(name = "activity") val activity: String,
    @Json(name = "type") val type: String
)


