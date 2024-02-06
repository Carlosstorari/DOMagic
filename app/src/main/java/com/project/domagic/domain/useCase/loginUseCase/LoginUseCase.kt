package com.project.domagic.domain.useCase.loginUseCase

interface LoginUseCase {
    fun logout()

    fun isLogged(): Boolean
}