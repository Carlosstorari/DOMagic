package com.project.domagic.domain.useCase.getListCardUseCase

import com.project.domagic.data.repository.getListCardRepository.GetCardListRepository
import com.project.domagic.data.repository.state.StateCardRepository
import com.project.domagic.data.service.ScryfallService
import com.project.domagic.domain.useCase.state.ScryFallStateUseCase

class GetListCardUseCaseImpl(
    private val repository: GetCardListRepository,
    private val service: ScryfallService
) : GetListCardUseCase {

    override suspend fun getListCardUseCase(colorCardName: String): ScryFallStateUseCase {
        val response = service.getListCards(colorCardName)
        return when (repository.getListCardByColor(colorCardName)) {
            StateCardRepository.Success(dataListCard = response.body()) -> {
                ScryFallStateUseCase.Success(listCard = response.body())
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