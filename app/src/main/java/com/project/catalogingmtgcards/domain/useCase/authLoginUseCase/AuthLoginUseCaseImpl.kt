package com.project.catalogingmtgcards.domain.useCase.authLoginUseCase

import androidx.lifecycle.LiveData
import com.project.catalogingmtgcards.data.repository.Resource
import com.project.catalogingmtgcards.data.repository.authLoginRepository.AuthLoginFirebaseRepository
import com.project.catalogingmtgcards.domain.model.User

class AuthLoginUseCaseImpl(private val repository: AuthLoginFirebaseRepository) : AuthLoginUseCase {
    override fun authLogin(user: User): LiveData<Resource<Boolean>> {
        return repository.authLogin(user)
    }
}