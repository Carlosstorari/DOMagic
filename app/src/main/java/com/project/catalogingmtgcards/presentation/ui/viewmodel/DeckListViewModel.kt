package com.project.catalogingmtgcards.presentation.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.project.catalogingmtgcards.core.DataCoordinator
import com.project.catalogingmtgcards.core.getStringDataStore
import com.project.catalogingmtgcards.data.repository.getUserDataRepository.GetUserDataRepositoryImpl
import com.project.catalogingmtgcards.domain.model.DeckItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DeckListViewModel(
    application: Application,
    private val getUserDataUseCase : GetUserDataRepositoryImpl
) : AndroidViewModel(application) {
    fun getListDecks(): LiveData<List<DeckItem>> {
        return getUserDataUseCase.getByDocumentId()
    }
}