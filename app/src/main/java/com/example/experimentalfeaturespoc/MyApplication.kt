package com.example.experimentalfeaturespoc

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        Timber.plant(Timber.DebugTree())
        appContext = this
    }

    companion object {
        var appContext: Context? = null
            private set
    }
}