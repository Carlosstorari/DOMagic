package com.project.catalogingmtgcards.domain.useCase

import com.project.catalogingmtgcards.data.repository.GetCardByNameRepository
import com.project.catalogingmtgcards.data.repository.StateCardRepository
import com.project.catalogingmtgcards.data.service.ScryfallService
import com.project.catalogingmtgcards.domain.ScryFallStateUseCase

class GetCardByNameUseCaseImpl(
    private val repository: GetCardByNameRepository,
    private val service: ScryfallService
): GetCardByNameUseCase {
    override suspend fun getCardByName(name: String): ScryFallStateUseCase {
        val response = service.getCardByName(name)
        //Repository retorna um ScryFallStateRepository do tipo Success ou Error
        return when (repository.getCardByName(name)) {
            StateCardRepository.Success(dataNamedCard = response.body()) -> {
                ScryFallStateUseCase.Success(cardNamed = response.body())
            }

            StateCardRepository.Error(exception = Exception("Falha ao buscar card")) -> {
                ScryFallStateUseCase.Error
            }

            else -> {
                ScryFallStateUseCase.Empty
            }
        }
    }
}