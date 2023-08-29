package com.project.catalogingmtgcards.di

import com.project.catalogingmtgcards.domain.repository.ScryFallRepository
import com.project.catalogingmtgcards.data.service.ScryfallService
import com.project.catalogingmtgcards.presentation.ui.viewmodel.ScryFallViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL_BASE = "https://api.scryfall.com/"

val retrofitModule = module {
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

val repositoryModule = module {
    single { ScryFallRepository(get()) }
}

val viewModelModule = module {
    viewModel { ScryFallViewModel(get(), get()) }
}

val appModules = listOf(
    retrofitModule, repositoryModule, viewModelModule
)