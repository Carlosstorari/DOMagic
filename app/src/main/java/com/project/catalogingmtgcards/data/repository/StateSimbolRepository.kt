package com.project.catalogingmtgcards.data.repository

import com.project.catalogingmtgcards.data.response.ListSymbolsResponseDto

sealed class StateSimbolRepository {
    data class Success(val manaCost: ListSymbolsResponseDto?) : StateSimbolRepository()
    data class Error(val exception: Exception) : StateSimbolRepository()
}