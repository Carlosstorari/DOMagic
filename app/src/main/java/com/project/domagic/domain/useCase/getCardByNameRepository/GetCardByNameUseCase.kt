package com.project.domagic.domain.useCase.getCardByNameRepository

import com.project.domagic.domain.useCase.state.ScryFallStateUseCase

interface GetCardByNameUseCase {
    suspend fun getCardByName(name: String): ScryFallStateUseCase
}