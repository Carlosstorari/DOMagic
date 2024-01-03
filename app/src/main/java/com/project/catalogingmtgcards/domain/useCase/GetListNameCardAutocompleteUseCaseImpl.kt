package com.project.catalogingmtgcards.domain.useCase

import com.project.catalogingmtgcards.data.repository.AutocompleteSearchRepository
import com.project.catalogingmtgcards.data.repository.StateCardRepository
import com.project.catalogingmtgcards.data.service.ScryfallService
import com.project.catalogingmtgcards.domain.ScryFallStateUseCase

class GetListNameCardAutocompleteUseCaseImpl(
    private val repository: AutocompleteSearchRepository,
    private val service: ScryfallService
): GetListNameCardAutocompleteUseCase {
    override suspend fun getListAutocomplete(query: String): ScryFallStateUseCase {
        val response = service.getListCardsAutoComplete(query)
        //Repository retorna um ScryFallStateRepository do tipo Success ou Error
        return when (repository.getListCardAutocomplete(query)) {
            StateCardRepository.Success(dataListCardNameAutocomplete = response.body()) -> {
                ScryFallStateUseCase.Success(listNameAutocomplete = response.body())
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