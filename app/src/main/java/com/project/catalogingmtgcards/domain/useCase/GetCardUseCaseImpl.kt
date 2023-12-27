package com.project.catalogingmtgcards.domain.useCase

import android.util.Log
import com.project.catalogingmtgcards.data.repository.CardRepository
import com.project.catalogingmtgcards.data.repository.StateCardRepository
import com.project.catalogingmtgcards.data.service.ScryfallService
import com.project.catalogingmtgcards.domain.ScryFallStateUseCase

class GetCardUseCaseImpl(
    private val repository: CardRepository,
    private val service: ScryfallService
) : GetCardByColorUseCase {

    override suspend fun getListCardUseCase(colorCardName: String): ScryFallStateUseCase {
        val response = service.getListCards(colorCardName)
        //Repository retorna um ScryFallStateRepository do tipo Success ou Error
        return when (repository.getListCardByColor(colorCardName)) {
            StateCardRepository.Success(data = response.body()) -> {
                Log.d("cartinhas", response.body().toString())
                ScryFallStateUseCase.Success(response.body(), null)
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