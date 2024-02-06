package com.project.domagic.domain.useCase.getCardByNameRepository

import com.project.domagic.data.repository.getCardByNameRepository.GetCardByNameRepository
import com.project.domagic.data.repository.state.StateCardRepository
import com.project.domagic.data.service.ScryfallService
import com.project.domagic.domain.useCase.state.ScryFallStateUseCase

class GetCardByNameUseCaseImpl(
    private val repository: GetCardByNameRepository,
    private val service: ScryfallService
): GetCardByNameUseCase {
    override suspend fun getCardByName(name: String): ScryFallStateUseCase {
        val response = service.getCardByName(name)
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