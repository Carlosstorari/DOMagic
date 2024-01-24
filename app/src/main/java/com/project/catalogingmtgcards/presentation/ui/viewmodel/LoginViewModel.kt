package com.project.catalogingmtgcards.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.catalogingmtgcards.data.repository.Resource
import com.project.catalogingmtgcards.domain.model.User
import com.project.catalogingmtgcards.domain.useCase.authLoginUseCase.AuthLoginUseCase
import com.project.catalogingmtgcards.domain.useCase.loginUseCase.LoginUseCase

class LoginViewModel(
    private val authLoginUseCase: AuthLoginUseCase,
    private val loginUseCase: LoginUseCase
): ViewModel() {

    fun authLogin(user: User): LiveData<Resource<Boolean>> {
        return authLoginUseCase.authLogin(user)
    }

    fun logout() {
        loginUseCase.logout()
    }

    fun isLogged(): Boolean {
        return loginUseCase.isLogged()
    }

    fun isNotLogged(): Boolean {
        return !isLogged()
    }

}