package com.project.catalogingmtgcards.domain.useCase

import com.project.catalogingmtgcards.data.response.CardResponseDto
import com.project.catalogingmtgcards.domain.ScryFallStateUseCase

interface GetSymbolManaCostUseCase {

    suspend fun getSymbolManaCost(colorCardName: List<CardResponseDto>): ScryFallStateUseCase
}