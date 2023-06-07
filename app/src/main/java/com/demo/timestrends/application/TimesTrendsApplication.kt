package com.demo.timestrends.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TimesTrendsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}