package com.project.domagic.data.repository.getImageManaSymbolRepository

import com.project.domagic.data.repository.state.StateSimbolRepository

interface SymbolRepository {
    suspend fun getSymbolManaCost(): StateSimbolRepository
}