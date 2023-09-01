package com.project.catalogingmtgcards.domain.useCase

import com.project.catalogingmtgcards.data.repository.ScryFallRepository
import com.project.catalogingmtgcards.data.repository.ScryFallStateRepository
import com.project.catalogingmtgcards.data.service.ScryfallService
import com.project.catalogingmtgcards.domain.ScryFallStateUseCase

class ScryFallUseCaseImpl(private val repository: ScryFallRepository, private val service: ScryfallService) :ScryFallUseCase {

    override suspend fun getListCard(colorCardName: String): ScryFallStateUseCase {
        val response = service.getListCards(colorCardName)
        //Repository retorna um ScryFallStateRepository do tipo Success ou Error
        return when(repository.getCard(colorCardName)) {
            ScryFallStateRepository.Success(data = response.body()) -> {
                ScryFallStateUseCase.Success(response.body())
            }
            ScryFallStateRepository.Error(exception = Exception("Falha ao buscar card")) -> {
                ScryFallStateUseCase.Error
            }
            else -> {
                ScryFallStateUseCase.Empty
            }
        }

    }
}