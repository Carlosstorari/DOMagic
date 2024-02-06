package com.project.domagic.domain.useCase.state

import com.project.domagic.data.response.CardListAutocompleteString
import com.project.domagic.data.response.CardListResponseDto
import com.project.domagic.data.response.CardResponseDto

sealed class ScryFallStateUseCase {
    data class Success(
        val cardNamed: CardResponseDto? = null,
        val listCard: CardListResponseDto? = null,
        val listNameAutocomplete: CardListAutocompleteString? = null,
        val symbologyMana: List<List<String>>? = null
    ) : ScryFallStateUseCase()

    object Error : ScryFallStateUseCase()// é object pq nao é passado parametro
    object Empty : ScryFallStateUseCase()
}