package com.project.catalogingmtgcards.presentation.di

import com.project.catalogingmtgcards.presentation.ui.viewmodel.ScryFallViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object PresentationModule {

    fun loadPresentationModule() {
        loadKoinModules(viewModelModule)
    }

    private val viewModelModule = module {
        viewModel { ScryFallViewModel(get(), get(), get()) }
    }
}