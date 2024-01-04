package com.project.catalogingmtgcards.data.repository.autocompleteSearchRepository

import com.project.catalogingmtgcards.data.repository.state.StateCardRepository

interface AutocompleteSearchRepository {

    suspend fun getListCardAutocomplete(query: String): StateCardRepository
}