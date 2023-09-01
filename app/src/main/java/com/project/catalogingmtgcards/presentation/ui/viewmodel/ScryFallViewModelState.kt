package com.project.catalogingmtgcards.presentation.ui.viewmodel

import com.project.catalogingmtgcards.data.response.CardListResponse

sealed class ScryFallViewModelState{
    data class Success(val card: CardListResponse): ScryFallViewModelState()
    object Error: ScryFallViewModelState()// é object pq nao é passado parametro
    object Empty: ScryFallViewModelState()
    object Loading: ScryFallViewModelState()
}