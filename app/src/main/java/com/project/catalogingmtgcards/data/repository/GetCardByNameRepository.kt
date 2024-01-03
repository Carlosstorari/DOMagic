package com.project.catalogingmtgcards.data.repository

interface GetCardByNameRepository {

    suspend fun getCardByName(name: String): StateCardRepository
}