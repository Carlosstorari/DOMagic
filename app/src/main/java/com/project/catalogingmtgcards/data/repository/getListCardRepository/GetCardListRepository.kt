package com.project.catalogingmtgcards.data.repository.getListCardRepository

import com.project.catalogingmtgcards.data.repository.state.StateCardRepository

interface GetCardListRepository {

    suspend fun getListCardByColor(colorCardName: String): StateCardRepository
}