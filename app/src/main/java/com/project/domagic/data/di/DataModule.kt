package com.project.domagic.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.domagic.data.repository.authLoginRepository.AuthLoginFirebaseRepository
import com.project.domagic.data.repository.authLoginRepository.AuthLoginFirebaseRepositoryImpl
import com.project.domagic.data.repository.autocompleteSearchRepository.AutocompleteSearchRepository
import com.project.domagic.data.repository.autocompleteSearchRepository.AutocompleteSearchRepositoryImpl
import com.project.domagic.data.repository.getListCardRepository.GetCardListRepository
import com.project.domagic.data.repository.getListCardRepository.GetCardListRepositoryImpl
import com.project.domagic.data.repository.getCardByNameRepository.GetCardByNameRepository
import com.project.domagic.data.repository.getCardByNameRepository.GetCardByNameRepositoryImpl
import com.project.domagic.data.repository.getImageManaSymbolRepository.SymbolRepository
import com.project.domagic.data.repository.getImageManaSymbolRepository.SymbolRepositoryImpl
import com.project.domagic.data.repository.getUserDataRepository.GetUserDataRepository
import com.project.domagic.data.repository.getUserDataRepository.GetUserDataRepositoryImpl
import com.project.domagic.data.repository.loginRepository.LoginRepository
import com.project.domagic.data.repository.loginRepository.LoginRepositoryImpl
import com.project.domagic.data.service.ScryfallService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {
    fun loadDataModule() {
        loadKoinModules(repositoryModule + retrofitModule + firebaseModule)
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
        single<GetCardByNameRepository> { GetCardByNameRepositoryImpl(get()) }
        single<AuthLoginFirebaseRepository> { AuthLoginFirebaseRepositoryImpl(get()) }
        single<LoginRepository> { LoginRepositoryImpl(get()) }
        single <GetUserDataRepository>{ GetUserDataRepositoryImpl(get()) }
    }

    private val firebaseModule = module {
        single<FirebaseAuth> { Firebase.auth }
        single<FirebaseFirestore> { Firebase.firestore }
    }
}