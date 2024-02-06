package com.project.domagic.presentation.ui.viewmodel

import com.project.domagic.domain.model.Card

sealed class ScryFallViewModelState{
    data class Success(val card: List<Card>): ScryFallViewModelState()
    object Error: ScryFallViewModelState()// é object pq nao é passado parametro
    object Empty: ScryFallViewModelState()
    object Loading: ScryFallViewModelState()
}