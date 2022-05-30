package com.example.network16052022.utils

import android.os.Handler
import android.os.Looper

fun ui(block: () -> Unit) {
    Handler(Looper.getMainLooper()).post(block)
}