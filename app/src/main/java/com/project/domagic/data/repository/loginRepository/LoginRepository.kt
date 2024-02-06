package com.project.domagic.data.repository.loginRepository

interface LoginRepository {
    fun logout()

    fun isLogged(): Boolean
}