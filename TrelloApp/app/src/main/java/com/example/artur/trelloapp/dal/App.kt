package com.example.artur.trelloapp.dal

import android.app.Application

class App: Application() {
    companion object Constants {
        const val TAG = "ObjectBoxExample"
    }

    override fun onCreate() {
        super.onCreate()
        ObjectBox.build(this)
    }
}