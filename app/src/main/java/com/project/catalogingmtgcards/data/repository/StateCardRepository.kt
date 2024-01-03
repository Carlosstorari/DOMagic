package com.project.catalogingmtgcards.data.repository

import com.project.catalogingmtgcards.data.response.CardListAutocompleteString
import com.project.catalogingmtgcards.data.response.CardListResponseDto
import com.project.catalogingmtgcards.data.response.CardResponseDto

sealed class StateCardRepository {
    data class Success(
        val dataNamedCard: CardResponseDto? = null,
        val dataListCard: CardListResponseDto? = null,
        val dataListCardNameAutocomplete: CardListAutocompleteString? = null
    ) : StateCardRepository()

    data class Error(val exception: Exception) : StateCardRepository()
}