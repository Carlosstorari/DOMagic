package com.project.domagic.domain.useCase.getImageManaSymbolUseCase

import com.project.domagic.data.response.CardResponseDto
import com.project.domagic.domain.useCase.state.ScryFallStateUseCase

interface GetSymbolManaCostUseCase {

    suspend fun getSymbolManaCost(colorCardName: List<CardResponseDto>): ScryFallStateUseCase
}