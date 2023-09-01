package com.project.catalogingmtgcards.data.repository

interface ScryFallRepository {

    suspend fun getCard(colorCardName: String): ScryFallStateRepository

}