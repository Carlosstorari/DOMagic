package com.project.catalogingmtgcards.domain.useCase

import com.project.catalogingmtgcards.domain.ScryFallStateUseCase

interface GetCardByNameUseCase {
    suspend fun getCardByName(name: String): ScryFallStateUseCase
}