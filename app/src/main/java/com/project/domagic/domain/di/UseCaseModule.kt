package com.project.domagic.domain.di

import com.project.domagic.domain.useCase.loginUseCase.LoginUseCase
import com.project.domagic.domain.useCase.loginUseCase.LoginUseCaseImpl
import com.project.domagic.domain.useCase.authLoginUseCase.AuthLoginUseCase
import com.project.domagic.domain.useCase.authLoginUseCase.AuthLoginUseCaseImpl
import com.project.domagic.domain.useCase.getListCardUseCase.GetListCardUseCase
import com.project.domagic.domain.useCase.getCardByNameRepository.GetCardByNameUseCase
import com.project.domagic.domain.useCase.getCardByNameRepository.GetCardByNameUseCaseImpl
import com.project.domagic.domain.useCase.getListCardUseCase.GetListCardUseCaseImpl
import com.project.domagic.domain.useCase.autocompleteSearchUseCase.GetListNameCardAutocompleteUseCase
import com.project.domagic.domain.useCase.autocompleteSearchUseCase.GetListNameCardAutocompleteUseCaseImpl
import com.project.domagic.domain.useCase.getImageManaSymbolUseCase.GetSymbolManaCostUseCase
import com.project.domagic.domain.useCase.getImageManaSymbolUseCase.GetSymbolManaCostUseCaseImpl
import com.project.domagic.domain.useCase.getUserDataUseCase.GetCardListDeckUseCase
import com.project.domagic.domain.useCase.getUserDataUseCase.GetCardListDeckUseCaseImpl
import com.project.domagic.domain.useCase.getUserDataUseCase.GetDeckByDocumentIdUseCase
import com.project.domagic.domain.useCase.getUserDataUseCase.GetDeckByDocumentIdUseCaseImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object UseCaseModule {

    fun loadUseCaseModule() {
        loadKoinModules(createUseCaseScryFallList())
    }

    private fun createUseCaseScryFallList() = module {
        single<GetListCardUseCase> { GetListCardUseCaseImpl(get(), get()) }
        single<GetSymbolManaCostUseCase> { GetSymbolManaCostUseCaseImpl(get(), get()) }
        single<GetListNameCardAutocompleteUseCase> {
            GetListNameCardAutocompleteUseCaseImpl(
                get(),
                get()
            )
        }
        single<GetCardByNameUseCase> { GetCardByNameUseCaseImpl(get(), get()) }
        single<AuthLoginUseCase> { AuthLoginUseCaseImpl(get()) }
        single<LoginUseCase> { LoginUseCaseImpl(get()) }
        single<GetCardListDeckUseCase> { GetCardListDeckUseCaseImpl(get()) }
        single<GetDeckByDocumentIdUseCase> { GetDeckByDocumentIdUseCaseImpl(get()) }
    }
}