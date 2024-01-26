package com.project.catalogingmtgcards.data.repository.getUserDataRepository

import androidx.lifecycle.LiveData
import com.project.catalogingmtgcards.domain.model.DeckItem

interface GetUserDataRepository {
    fun getDeckByDocumentId(): LiveData<List<DeckItem>>

    fun getCardListDeckDetail(index: Int): LiveData<List<String>>
}