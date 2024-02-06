package com.project.domagic.presentation.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.project.domagic.domain.model.DeckItem
import com.project.domagic.domain.useCase.getUserDataUseCase.GetCardListDeckUseCase
import com.project.domagic.domain.useCase.getUserDataUseCase.GetDeckByDocumentIdUseCase

class DeckListViewModel(
    application: Application,
    private val getDeckUseCase : GetDeckByDocumentIdUseCase,
    private val getCardListDeckUseCase: GetCardListDeckUseCase
) : AndroidViewModel(application) {
    fun getListDecks(): LiveData<List<DeckItem>> {
        return getDeckUseCase.getDeck()
    }
}