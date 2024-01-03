package com.project.catalogingmtgcards.domain.di

import com.project.catalogingmtgcards.domain.useCase.GetCardByColorUseCase
import com.project.catalogingmtgcards.domain.useCase.GetCardByNameUseCase
import com.project.catalogingmtgcards.domain.useCase.GetCardByNameUseCaseImpl
import com.project.catalogingmtgcards.domain.useCase.GetCardUseCaseImpl
import com.project.catalogingmtgcards.domain.useCase.GetListNameCardAutocompleteUseCase
import com.project.catalogingmtgcards.domain.useCase.GetListNameCardAutocompleteUseCaseImpl
import com.project.catalogingmtgcards.domain.useCase.GetSymbolManaCostUseCase
import com.project.catalogingmtgcards.domain.useCase.GetSymbolManaCostUseCaseImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object UseCaseModule {

    fun loadUseCaseModule() {
        loadKoinModules(createUseCaseScryFallList())
    }

    private fun createUseCaseScryFallList() = module {
        single<GetCardByColorUseCase> { GetCardUseCaseImpl(get(), get()) }
        single<GetSymbolManaCostUseCase> { GetSymbolManaCostUseCaseImpl(get(), get()) }
        single<GetListNameCardAutocompleteUseCase> {
            GetListNameCardAutocompleteUseCaseImpl(
                get(),
                get()
            )
        }
        single<GetCardByNameUseCase> { GetCardByNameUseCaseImpl(get(), get()) }
    }
}