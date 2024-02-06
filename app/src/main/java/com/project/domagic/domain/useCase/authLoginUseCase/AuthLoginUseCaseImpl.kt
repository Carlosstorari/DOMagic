package com.project.domagic.domain.useCase.authLoginUseCase

import androidx.lifecycle.LiveData
import com.project.domagic.data.repository.Resource
import com.project.domagic.data.repository.authLoginRepository.AuthLoginFirebaseRepository
import com.project.domagic.domain.model.User

class AuthLoginUseCaseImpl(private val repository: AuthLoginFirebaseRepository) : AuthLoginUseCase {
    override fun authLogin(user: User): LiveData<Resource<Boolean>> {
        return repository.authLogin(user)
    }
}