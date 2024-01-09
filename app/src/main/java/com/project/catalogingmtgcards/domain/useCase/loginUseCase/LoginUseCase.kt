package com.project.catalogingmtgcards.domain.useCase.loginUseCase

interface LoginUseCase {
    fun logout()

    fun isLogged(): Boolean
}