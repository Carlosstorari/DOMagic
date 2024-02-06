package com.project.domagic.data.repository.autocompleteSearchRepository

import com.project.domagic.data.repository.state.StateCardRepository

interface AutocompleteSearchRepository {

    suspend fun getListCardAutocomplete(query: String): StateCardRepository
}