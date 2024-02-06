package com.project.domagic.domain.useCase.getListCardUseCase

import com.project.domagic.domain.useCase.state.ScryFallStateUseCase

//Chama o repository e devolve um state Success(Response) ou Error
//Se retornar Success converte o response em model

interface GetListCardUseCase {
    suspend fun getListCardUseCase(colorCardName: String): ScryFallStateUseCase
}