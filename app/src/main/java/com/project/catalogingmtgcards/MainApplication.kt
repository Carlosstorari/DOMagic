package com.project.catalogingmtgcards

import android.app.Application
import com.project.catalogingmtgcards.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MainApplication)
            modules(appModules)
        }
    }

}