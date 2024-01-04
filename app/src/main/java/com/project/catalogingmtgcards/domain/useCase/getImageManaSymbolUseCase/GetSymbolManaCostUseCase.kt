package com.project.catalogingmtgcards.domain.useCase.getImageManaSymbolUseCase

import com.project.catalogingmtgcards.data.response.CardResponseDto
import com.project.catalogingmtgcards.domain.useCase.state.ScryFallStateUseCase

interface GetSymbolManaCostUseCase {

    suspend fun getSymbolManaCost(colorCardName: List<CardResponseDto>): ScryFallStateUseCase
}