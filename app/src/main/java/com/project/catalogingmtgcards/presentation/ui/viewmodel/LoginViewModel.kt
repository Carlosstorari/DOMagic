package com.project.catalogingmtgcards.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.catalogingmtgcards.data.repository.FirebaseAuthRepository
import com.project.catalogingmtgcards.data.repository.Resource
import com.project.catalogingmtgcards.domain.model.User

class LoginViewModel(private val fireBaseAuthRepository: FirebaseAuthRepository): ViewModel() {
    private val _bottomBarIsVisible: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var bottomBarIsVisible: LiveData<Boolean> = _bottomBarIsVisible


    fun authLogin(user: User): LiveData<Resource<Boolean>> {

        return fireBaseAuthRepository.authLogin(user)
    }

    fun logout() {
        fireBaseAuthRepository.logout()
    }

    fun isLogged(): Boolean {
        return fireBaseAuthRepository.isLogged()
    }

    fun isNotLogged(): Boolean {
        return !isLogged()
    }

    fun bottomBarIsVisibleState() {
        if (isLogged()) _bottomBarIsVisible.value = true
        if (isNotLogged()) _bottomBarIsVisible.value = false
    }

}