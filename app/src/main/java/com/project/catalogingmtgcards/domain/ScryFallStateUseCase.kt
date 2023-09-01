package com.project.catalogingmtgcards.domain

import com.project.catalogingmtgcards.data.response.CardListResponse

sealed class ScryFallStateUseCase {
    data class Success(val card: CardListResponse?): ScryFallStateUseCase()
    object Error: ScryFallStateUseCase()// é object pq nao é passado parametro
    object Empty: ScryFallStateUseCase()
}