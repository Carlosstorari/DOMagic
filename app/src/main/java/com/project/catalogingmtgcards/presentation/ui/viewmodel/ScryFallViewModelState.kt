package com.project.catalogingmtgcards.presentation.ui.viewmodel

import com.project.catalogingmtgcards.domain.model.Card

sealed class ScryFallViewModelState{
    data class Success(val card: List<Card>): ScryFallViewModelState()
    object Error: ScryFallViewModelState()// é object pq nao é passado parametro
    object Empty: ScryFallViewModelState()
    object Loading: ScryFallViewModelState()
}