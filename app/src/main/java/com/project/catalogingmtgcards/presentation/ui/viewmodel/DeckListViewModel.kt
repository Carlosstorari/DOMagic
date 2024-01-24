package com.project.catalogingmtgcards.presentation.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.project.catalogingmtgcards.data.repository.getUserDataRepository.GetUserDataRepositoryImpl
import com.project.catalogingmtgcards.domain.model.DeckItem

class DeckListViewModel(
    application: Application,
    private val getUserDataUseCase : GetUserDataRepositoryImpl
) : AndroidViewModel(application) {
    fun getListDecks(): LiveData<List<DeckItem>> {
        return getUserDataUseCase.getByDocumentId()
    }
}