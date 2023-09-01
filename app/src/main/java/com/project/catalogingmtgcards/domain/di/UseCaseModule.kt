package com.project.catalogingmtgcards.domain.di

import com.project.catalogingmtgcards.domain.useCase.ScryFallUseCase
import com.project.catalogingmtgcards.domain.useCase.ScryFallUseCaseImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object UseCaseModule {

    fun loadUseCaseModule(){
        loadKoinModules(createUseCaseScryFallList())
    }

    private fun createUseCaseScryFallList() = module {
        single<ScryFallUseCase> { ScryFallUseCaseImpl(get(), get()) }
    }
}