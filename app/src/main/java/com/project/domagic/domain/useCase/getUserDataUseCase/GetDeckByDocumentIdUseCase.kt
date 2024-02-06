package com.project.domagic.domain.useCase.getUserDataUseCase

import androidx.lifecycle.LiveData
import com.project.domagic.domain.model.DeckItem

interface GetDeckByDocumentIdUseCase {
    fun getDeck() : LiveData<List<DeckItem>>
}