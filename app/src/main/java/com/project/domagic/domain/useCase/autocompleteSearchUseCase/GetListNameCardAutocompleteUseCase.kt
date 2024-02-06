package com.project.domagic.domain.useCase.autocompleteSearchUseCase

import com.project.domagic.domain.useCase.state.ScryFallStateUseCase

interface GetListNameCardAutocompleteUseCase {
    suspend fun getListAutocomplete(query: String): ScryFallStateUseCase
}