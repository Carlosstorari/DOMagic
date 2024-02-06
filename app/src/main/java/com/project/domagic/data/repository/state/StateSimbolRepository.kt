package com.project.domagic.data.repository.state

import com.project.domagic.data.response.ListSymbolsResponseDto

sealed class StateSimbolRepository {
    data class Success(val manaCost: ListSymbolsResponseDto?) : StateSimbolRepository()
    data class Error(val exception: Exception) : StateSimbolRepository()
}