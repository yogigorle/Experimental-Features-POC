package com.example.experimentalfeaturespoc

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        appContext = this
    }

    companion object {
        var appContext: Context? = null
            private set
    }
}