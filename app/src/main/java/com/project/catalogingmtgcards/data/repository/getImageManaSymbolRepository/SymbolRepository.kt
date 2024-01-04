package com.project.catalogingmtgcards.data.repository.getImageManaSymbolRepository

import com.project.catalogingmtgcards.data.repository.state.StateSimbolRepository

interface SymbolRepository {
    suspend fun getSymbolManaCost(): StateSimbolRepository
}