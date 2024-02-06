package com.project.domagic.data.repository.authLoginRepository

import androidx.lifecycle.LiveData
import com.project.domagic.data.repository.Resource
import com.project.domagic.domain.model.User

interface AuthLoginFirebaseRepository {
    fun authLogin(user: User): LiveData<Resource<Boolean>>
}