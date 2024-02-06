package com.project.domagic.data.repository.authLoginRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.project.domagic.R
import com.project.domagic.data.repository.Resource
import com.project.domagic.domain.model.User

class AuthLoginFirebaseRepositoryImpl(private val firebaseAuth: FirebaseAuth) :
    AuthLoginFirebaseRepository {
    override fun authLogin(user: User): LiveData<Resource<Boolean>> {
        val liveData = MutableLiveData<Resource<Boolean>>()
        try {
            firebaseAuth.signInWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        liveData.value = Resource(true)
                    } else {
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