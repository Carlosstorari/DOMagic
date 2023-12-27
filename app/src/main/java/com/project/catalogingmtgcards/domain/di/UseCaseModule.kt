package com.project.catalogingmtgcards.domain.di

import com.project.catalogingmtgcards.domain.useCase.GetCardByColorUseCase
import com.project.catalogingmtgcards.domain.useCase.GetCardUseCaseImpl
import com.project.catalogingmtgcards.domain.useCase.GetSymbolManaCostUseCase
import com.project.catalogingmtgcards.domain.useCase.GetSymbolManaCostUseCaseImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object UseCaseModule {

    fun loadUseCaseModule(){
        loadKoinModules(createUseCaseScryFallList())
    }

    private fun createUseCaseScryFallList() = module {
        single<GetCardByColorUseCase> { GetCardUseCaseImpl(get(), get()) }
        single<GetSymbolManaCostUseCase> { GetSymbolManaCostUseCaseImpl(get(), get()) }
    }
}