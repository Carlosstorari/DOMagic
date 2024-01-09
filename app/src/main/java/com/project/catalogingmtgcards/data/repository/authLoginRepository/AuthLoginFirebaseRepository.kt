package com.project.catalogingmtgcards.data.repository.authLoginRepository

import androidx.lifecycle.LiveData
import com.project.catalogingmtgcards.data.repository.Resource
import com.project.catalogingmtgcards.domain.model.User

interface AuthLoginFirebaseRepository {
    fun authLogin(user: User): LiveData<Resource<Boolean>>
}