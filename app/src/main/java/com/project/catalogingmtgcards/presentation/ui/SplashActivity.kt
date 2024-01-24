package com.project.catalogingmtgcards.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.catalogingmtgcards.R
import com.project.catalogingmtgcards.presentation.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.putExtra("isNotLogged", isNotLogged())
        startActivity(intent)
    }

    private fun isNotLogged() = loginViewModel.isNotLogged()


}