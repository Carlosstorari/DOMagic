package com.project.domagic.data.repository.getListCardRepository

import com.project.domagic.data.repository.state.StateCardRepository

interface GetCardListRepository {

    suspend fun getListCardByColor(colorCardName: String): StateCardRepository
}