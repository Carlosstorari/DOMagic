package com.project.catalogingmtgcards.data.repository

import com.project.catalogingmtgcards.data.response.ListSymbolsResponseDto

interface SimbolRepository {
    suspend fun getSymbolManaCost(): StateSimbolRepository
}