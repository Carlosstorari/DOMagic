package com.project.catalogingmtgcards.domain.useCase.getUserDataUseCase

import androidx.lifecycle.LiveData
import com.project.catalogingmtgcards.data.repository.getUserDataRepository.GetUserDataRepository

class GetCardListDeckUseCaseImpl(val repository: GetUserDataRepository): GetCardListDeckUseCase {
    override fun getCardListDeck(deckId: String): LiveData<List<String>> {
        return repository.getCardListDeckDetail(deckId)
    }
}