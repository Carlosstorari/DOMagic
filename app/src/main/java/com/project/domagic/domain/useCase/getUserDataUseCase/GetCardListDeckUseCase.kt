package com.project.domagic.domain.useCase.getUserDataUseCase

import androidx.lifecycle.LiveData

interface GetCardListDeckUseCase {
    fun getCardListDeck(deckId: String): LiveData<List<String>>
}