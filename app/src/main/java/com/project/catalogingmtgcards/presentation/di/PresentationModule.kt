package com.project.catalogingmtgcards.presentation.di

import com.project.catalogingmtgcards.presentation.adapter.DeckListAdapter
import com.project.catalogingmtgcards.presentation.ui.viewmodel.DeckDetailViewModel
import com.project.catalogingmtgcards.presentation.ui.viewmodel.DeckListViewModel
import com.project.catalogingmtgcards.presentation.ui.viewmodel.LoginViewModel
import com.project.catalogingmtgcards.presentation.ui.viewmodel.SearchCardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object PresentationModule {

    fun loadPresentationModule() {
        loadKoinModules(viewModelModule)
    }

    private val viewModelModule = module {
        viewModel { SearchCardViewModel(get(), get(), get(), get(), get()) }
        viewModel { DeckListViewModel(get(), get(), get()) }
        viewModel { LoginViewModel(get(), get()) }
        viewModel <DeckDetailViewModel>{ (deckId: String) -> DeckDetailViewModel(deckId, get(), get(), get(), get()) }
        factory { DeckListAdapter(get()) }
    }
}