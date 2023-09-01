package com.project.catalogingmtgcards

import android.app.Application
import com.project.catalogingmtgcards.data.di.DataModule
import com.project.catalogingmtgcards.domain.di.UseCaseModule
import com.project.catalogingmtgcards.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MainApplication)
        }
        DataModule.loadDataModule()
        UseCaseModule.loadUseCaseModule()
        PresentationModule.loadPresentationModule()
    }

}