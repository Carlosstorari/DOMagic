package com.project.catalogingmtgcards.data.repository.loginRepository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginRepositoryImpl(private val firebaseAuth: FirebaseAuth): LoginRepository {
    override fun logout() {
        firebaseAuth.signOut()
    }

    override fun isLogged(): Boolean {
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        if (firebaseUser != null) {
            return true
        }
        return false
    }
}