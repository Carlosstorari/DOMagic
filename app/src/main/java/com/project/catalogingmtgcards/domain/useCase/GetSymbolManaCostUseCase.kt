package com.project.catalogingmtgcards.domain.useCase

import com.project.catalogingmtgcards.data.response.CardResponse
import com.project.catalogingmtgcards.domain.ScryFallStateUseCase

interface GetSymbolManaCostUseCase {

    suspend fun getSymbolManaCost(colorCardName: List<CardResponse>): ScryFallStateUseCase
}