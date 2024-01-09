package com.project.catalogingmtgcards.data.repository.loginRepository

interface LoginRepository {
    fun logout()

    fun isLogged(): Boolean
}