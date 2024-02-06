package com.project.domagic.domain.useCase.getUserDataUseCase

import androidx.lifecycle.LiveData
import com.project.domagic.data.repository.getUserDataRepository.GetUserDataRepository
import com.project.domagic.domain.model.DeckItem

class GetDeckByDocumentIdUseCaseImpl(val repository: GetUserDataRepository): GetDeckByDocumentIdUseCase {
    override fun getDeck(): LiveData<List<DeckItem>> {
        return repository.getDeckByDocumentId()
    }
}