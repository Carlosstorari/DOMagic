package com.project.catalogingmtgcards.domain

import com.project.catalogingmtgcards.data.response.CardListResponseDto

sealed class ScryFallStateUseCase {
    data class Success(
        val card: CardListResponseDto?,
        val symbologyMana: List<List<String>>?): ScryFallStateUseCase()
    object Error: ScryFallStateUseCase()// é object pq nao é passado parametro
    object Empty: ScryFallStateUseCase()
}