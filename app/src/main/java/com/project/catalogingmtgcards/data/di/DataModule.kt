package com.project.catalogingmtgcards.data.di

import com.project.catalogingmtgcards.data.repository.AutocompleteSearchRepository
import com.project.catalogingmtgcards.data.repository.AutocompleteSearchRepositoryImpl
import com.project.catalogingmtgcards.data.repository.CardRepository
import com.project.catalogingmtgcards.data.repository.CardRepositoryImpl
import com.project.catalogingmtgcards.data.repository.GetCardByNameRepository
import com.project.catalogingmtgcards.data.repository.GetCardByNameRepositoryImpl
import com.project.catalogingmtgcards.data.repository.SimbolRepository
import com.project.catalogingmtgcards.data.repository.SimbolRepositoryImpl
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
        single<CardRepository> { CardRepositoryImpl(get()) }
        single<SimbolRepository> { SimbolRepositoryImpl(get()) }
        single<AutocompleteSearchRepository> { AutocompleteSearchRepositoryImpl(get()) }
        single <GetCardByNameRepository>{ GetCardByNameRepositoryImpl(get()) }
    }
}