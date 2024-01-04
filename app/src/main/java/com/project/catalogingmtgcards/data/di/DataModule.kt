package com.project.catalogingmtgcards.data.di

import com.project.catalogingmtgcards.data.repository.autocompleteSearchRepository.AutocompleteSearchRepository
import com.project.catalogingmtgcards.data.repository.autocompleteSearchRepository.AutocompleteSearchRepositoryImpl
import com.project.catalogingmtgcards.data.repository.getListCardRepository.GetCardListRepository
import com.project.catalogingmtgcards.data.repository.getListCardRepository.GetCardListRepositoryImpl
import com.project.catalogingmtgcards.data.repository.getCardByNameRepository.GetCardByNameRepository
import com.project.catalogingmtgcards.data.repository.getCardByNameRepository.GetCardByNameRepositoryImpl
import com.project.catalogingmtgcards.data.repository.getImageManaSymbolRepository.SymbolRepository
import com.project.catalogingmtgcards.data.repository.getImageManaSymbolRepository.SymbolRepositoryImpl
import com.project.catalogingmtgcards.data.service.ScryfallService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {
    fun loadDataModule() {
        loadKoinModules(repositoryModule + retrofitModule)
    }

    private const val URL_BASE = "https://api.scryfall.com/"

    private val retrofitModule = module {
        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        single<OkHttpClient> {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        }
        single<ScryfallService> { get<Retrofit>().create(ScryfallService::class.java) }
    }

    private val repositoryModule = module {
        single<GetCardListRepository> { GetCardListRepositoryImpl(get()) }
        single<SymbolRepository> { SymbolRepositoryImpl(get()) }
        single<AutocompleteSearchRepository> { AutocompleteSearchRepositoryImpl(get()) }
        single <GetCardByNameRepository>{ GetCardByNameRepositoryImpl(get()) }
    }
}