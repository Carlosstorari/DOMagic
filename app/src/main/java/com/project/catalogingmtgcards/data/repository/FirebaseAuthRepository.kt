package com.project.catalogingmtgcards.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.project.catalogingmtgcards.R
import com.project.catalogingmtgcards.domain.model.User

private const val TAG = "firebaseAuthRepository"

class FirebaseAuthRepository(private val firebaseAuth: FirebaseAuth) {

    fun logout() {
        firebaseAuth.signOut()
    }

    fun isLogged(): Boolean {
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        if (firebaseUser != null) {
            return true
        }
        return false
    }

    fun authLogin(user: User): LiveData<Resource<Boolean>> {
        val liveData = MutableLiveData<Resource<Boolean>>()
        try {
            firebaseAuth.signInWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        liveData.value = Resource(true)
                    } else {
                        Log.e(TAG, "auth: ", task.exception)
                        val errorMessage = catchAuthError(task.exception)
                        liveData.value = Resource(false, errorMessage)
                    }
                }
        } catch (e: IllegalArgumentException) {
            liveData.value = Resource(false, R.string.e_mail_or_password_empty)
        }
        return liveData
    }

    private fun catchAuthError(exception: Exception?): Int = when (exception) {
        is FirebaseAuthInvalidUserException,
        is FirebaseAuthInvalidCredentialsException -> R.string.email_or_senha_incorrect

        else -> R.string.unknown_error
    }

}