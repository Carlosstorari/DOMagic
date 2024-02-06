package com.project.domagic.data.repository.getCardByNameRepository

import com.project.domagic.data.repository.state.StateCardRepository

interface GetCardByNameRepository {

    suspend fun getCardByName(name: String): StateCardRepository
}