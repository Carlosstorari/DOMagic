package com.project.catalogingmtgcards.domain.useCase.getUserDataUseCase

import androidx.lifecycle.LiveData
import com.project.catalogingmtgcards.domain.model.DeckItem

interface GetDeckByDocumentIdUseCase {
    fun getDeck() : LiveData<List<DeckItem>>
}