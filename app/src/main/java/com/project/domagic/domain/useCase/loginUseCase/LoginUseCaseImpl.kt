package com.project.domagic.domain.useCase.loginUseCase

import com.project.domagic.data.repository.loginRepository.LoginRepository

class LoginUseCaseImpl(private val repository: LoginRepository) : LoginUseCase {
    override fun logout() {
        repository.logout()
    }

    override fun isLogged(): Boolean {
        return repository.isLogged()
    }
}