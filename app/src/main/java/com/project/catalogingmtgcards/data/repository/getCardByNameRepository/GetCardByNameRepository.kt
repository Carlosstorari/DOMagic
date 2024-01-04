package com.project.catalogingmtgcards.data.repository.getCardByNameRepository

import com.project.catalogingmtgcards.data.repository.state.StateCardRepository

interface GetCardByNameRepository {

    suspend fun getCardByName(name: String): StateCardRepository
}