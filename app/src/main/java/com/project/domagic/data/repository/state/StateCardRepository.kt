package com.project.domagic.data.repository.state

import com.project.domagic.data.response.CardListAutocompleteString
import com.project.domagic.data.response.CardListResponseDto
import com.project.domagic.data.response.CardResponseDto

sealed class StateCardRepository {
    data class Success(
        val dataNamedCard: CardResponseDto? = null,
        val dataListCard: CardListResponseDto? = null,
        val dataListCardNameAutocomplete: CardListAutocompleteString? = null
    ) : StateCardRepository()

    data class Error(val exception: Exception) : StateCardRepository()
}