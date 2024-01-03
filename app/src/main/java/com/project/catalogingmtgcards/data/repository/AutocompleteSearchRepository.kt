package com.project.catalogingmtgcards.data.repository

interface AutocompleteSearchRepository {

    suspend fun getListCardAutocomplete(query: String): StateCardRepository
}