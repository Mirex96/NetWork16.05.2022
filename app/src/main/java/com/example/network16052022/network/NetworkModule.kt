package com.example.network16052022.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit


object NetworkModule {
    private val Client = OkHttpClient().newBuilder()
        .readTimeout(30_000, TimeUnit.MILLISECONDS)
        .connectTimeout(30_000, TimeUnit.MILLISECONDS)
        .writeTimeout(30_000, TimeUnit.MILLISECONDS)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.boredapi.com/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(Client)
        .build()

    val api = retrofit.create<SimpleApi>()


}




