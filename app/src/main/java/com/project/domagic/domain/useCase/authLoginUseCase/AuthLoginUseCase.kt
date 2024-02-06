package com.project.domagic.domain.useCase.authLoginUseCase

import androidx.lifecycle.LiveData
import com.project.domagic.data.repository.Resource
import com.project.domagic.domain.model.User

interface AuthLoginUseCase {
    fun authLogin(user: User): LiveData<Resource<Boolean>>
}