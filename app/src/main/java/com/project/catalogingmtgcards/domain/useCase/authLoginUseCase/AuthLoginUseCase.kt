package com.project.catalogingmtgcards.domain.useCase.authLoginUseCase

import androidx.lifecycle.LiveData
import com.project.catalogingmtgcards.data.repository.Resource
import com.project.catalogingmtgcards.domain.model.User

interface AuthLoginUseCase {
    fun authLogin(user: User): LiveData<Resource<Boolean>>
}