package com.ramesh.movie.mvvm

import android.app.Application
import com.facebook.stetho.Stetho
import com.ramesh.movie.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}