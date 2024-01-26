package com.project.catalogingmtgcards.domain.useCase.getUserDataUseCase

import androidx.lifecycle.LiveData

interface GetCardListDeckUseCase {
    fun getCardListDeck(index: Int): LiveData<List<String>>
}