package com.project.catalogingmtgcards.domain.useCase.loginUseCase

import com.project.catalogingmtgcards.data.repository.loginRepository.LoginRepository

class LoginUseCaseImpl(private val repository: LoginRepository) : LoginUseCase {
    override fun logout() {
        repository.logout()
    }

    override fun isLogged(): Boolean {
        return repository.isLogged()
    }
}