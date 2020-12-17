package com.example.rxjava

import android.app.Application
import com.example.rxjava.di.appmodules
import org.koin.android.ext.android.startKoin

class SearchApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin ( this, appmodules )

    }
}