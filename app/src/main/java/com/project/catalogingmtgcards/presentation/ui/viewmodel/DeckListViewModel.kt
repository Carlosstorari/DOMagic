package com.project.catalogingmtgcards.presentation.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.project.catalogingmtgcards.domain.model.DeckItem
import com.project.catalogingmtgcards.domain.useCase.getUserDataUseCase.GetCardListDeckUseCase
import com.project.catalogingmtgcards.domain.useCase.getUserDataUseCase.GetDeckByDocumentIdUseCase

class DeckListViewModel(
    application: Application,
    private val getDeckUseCase : GetDeckByDocumentIdUseCase,
    private val getCardListDeckUseCase: GetCardListDeckUseCase
) : AndroidViewModel(application) {
    fun getListDecks(): LiveData<List<DeckItem>> {
        return getDeckUseCase.getDeck()
    }
}