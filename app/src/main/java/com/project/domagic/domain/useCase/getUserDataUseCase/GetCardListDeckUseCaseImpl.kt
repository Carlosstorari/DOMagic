package com.project.domagic.domain.useCase.getUserDataUseCase

import androidx.lifecycle.LiveData
import com.project.domagic.data.repository.getUserDataRepository.GetUserDataRepository

class GetCardListDeckUseCaseImpl(val repository: GetUserDataRepository): GetCardListDeckUseCase {
    override fun getCardListDeck(deckId: String): LiveData<List<String>> {
        return repository.getCardListDeckDetail(deckId)
    }
}