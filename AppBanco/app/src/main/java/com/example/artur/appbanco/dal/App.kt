package com.example.artur.appbanco.dal

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