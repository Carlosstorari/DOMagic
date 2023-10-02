package com.project.catalogingmtgcards.data.repository

interface CardRepository {

    suspend fun getListCardByColor(colorCardName: String): StateCardRepository
}