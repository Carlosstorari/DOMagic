package com.project.catalogingmtgcards.domain.useCase.getCardByNameRepository

import com.project.catalogingmtgcards.domain.useCase.state.ScryFallStateUseCase

interface GetCardByNameUseCase {
    suspend fun getCardByName(name: String): ScryFallStateUseCase
}