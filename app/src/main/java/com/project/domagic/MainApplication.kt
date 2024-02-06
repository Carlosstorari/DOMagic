package com.project.domagic

import android.app.Application
import com.project.domagic.data.di.DataModule
import com.project.domagic.domain.di.UseCaseModule
import com.project.domagic.presentation.di.PresentationModule
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