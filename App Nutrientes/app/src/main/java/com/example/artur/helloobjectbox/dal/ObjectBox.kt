package com.example.artur.helloobjectbox.dal

import android.content.Context
import com.example.artur.helloobjectbox.Model.MyObjectBox
import io.objectbox.BoxStore

object ObjectBox {
    lateinit var boxStore: BoxStore
        private set

    fun build(context: Context) {
        // This is the minimal setup required on Android
        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext).build()

        // Example how you could use a custom dir in "external storage"
        // (Android 6+ note: give the app storage permission in app info settings)
//        val directory = File(Environment.getExternalStorageDirectory(), "objectbox-notes");
//        boxStore = MyObjectBox.builder().androidContext(context.applicationContext)
//                .directory(directory)
//                .build()
    }
}
