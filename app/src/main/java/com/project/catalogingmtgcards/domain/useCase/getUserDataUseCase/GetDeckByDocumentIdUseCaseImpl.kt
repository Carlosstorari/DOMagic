package com.project.catalogingmtgcards.domain.useCase.getUserDataUseCase

import androidx.lifecycle.LiveData
import com.project.catalogingmtgcards.data.repository.getUserDataRepository.GetUserDataRepository
import com.project.catalogingmtgcards.domain.model.DeckItem

class GetDeckByDocumentIdUseCaseImpl(val repository: GetUserDataRepository): GetDeckByDocumentIdUseCase {
    override fun getDeck(): LiveData<List<DeckItem>> {
        return repository.getDeckByDocumentId()
    }
}