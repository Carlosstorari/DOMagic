package com.project.catalogingmtgcards.domain.useCase.getListCardUseCase

import com.project.catalogingmtgcards.data.repository.getListCardRepository.GetCardListRepository
import com.project.catalogingmtgcards.data.repository.state.StateCardRepository
import com.project.catalogingmtgcards.data.service.ScryfallService
import com.project.catalogingmtgcards.domain.useCase.state.ScryFallStateUseCase

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