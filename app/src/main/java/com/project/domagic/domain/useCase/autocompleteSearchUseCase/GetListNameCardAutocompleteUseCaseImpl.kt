package com.project.domagic.domain.useCase.autocompleteSearchUseCase

import com.project.domagic.data.repository.autocompleteSearchRepository.AutocompleteSearchRepository
import com.project.domagic.data.repository.state.StateCardRepository
import com.project.domagic.data.service.ScryfallService
import com.project.domagic.domain.useCase.state.ScryFallStateUseCase

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