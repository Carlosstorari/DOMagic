package com.project.catalogingmtgcards.domain.useCase.autocompleteSearchUseCase

import com.project.catalogingmtgcards.domain.useCase.state.ScryFallStateUseCase

interface GetListNameCardAutocompleteUseCase {
    suspend fun getListAutocomplete(query: String): ScryFallStateUseCase
}