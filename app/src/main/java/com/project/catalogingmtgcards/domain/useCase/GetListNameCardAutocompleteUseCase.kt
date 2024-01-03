package com.project.catalogingmtgcards.domain.useCase

import com.project.catalogingmtgcards.domain.ScryFallStateUseCase

interface GetListNameCardAutocompleteUseCase {
    suspend fun getListAutocomplete(query: String): ScryFallStateUseCase
}