package com.project.domagic.data.repository.getUserDataRepository

import androidx.lifecycle.LiveData
import com.project.domagic.domain.model.DeckItem

interface GetUserDataRepository {
    fun getDeckByDocumentId(): LiveData<List<DeckItem>>

    fun getCardListDeckDetail(deckId: String): LiveData<List<String>>
}