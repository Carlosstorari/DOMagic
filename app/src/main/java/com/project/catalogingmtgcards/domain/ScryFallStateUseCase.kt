package com.project.catalogingmtgcards.domain

import com.project.catalogingmtgcards.data.response.CardListResponse
import com.project.catalogingmtgcards.data.response.SymbologyManaCostResponse

sealed class ScryFallStateUseCase {
    data class Success(
        val card: CardListResponse?,
        val symbologyMana: List<List<String>>?): ScryFallStateUseCase()
    object Error: ScryFallStateUseCase()// é object pq nao é passado parametro
    object Empty: ScryFallStateUseCase()
}