package com.cdrussell.recyclerviewtransition

import android.app.Application
import timber.log.Timber

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}