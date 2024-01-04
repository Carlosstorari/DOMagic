package com.project.catalogingmtgcards.domain.useCase.autocompleteSearchUseCase

import com.project.catalogingmtgcards.data.repository.autocompleteSearchRepository.AutocompleteSearchRepository
import com.project.catalogingmtgcards.data.repository.state.StateCardRepository
import com.project.catalogingmtgcards.data.service.ScryfallService
import com.project.catalogingmtgcards.domain.useCase.state.ScryFallStateUseCase

class GetListNameCardAutocompleteUseCaseImpl(
    private val repository: AutocompleteSearchRepository,
    private val service: ScryfallService
): GetListNameCardAutocompleteUseCase {
    override suspend fun getListAutocomplete(query: String): ScryFallStateUseCase {
        val response = service.getListCardsAutoComplete(query)
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