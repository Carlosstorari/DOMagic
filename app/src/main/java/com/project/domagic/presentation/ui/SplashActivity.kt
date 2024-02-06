package com.project.domagic.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.project.domagic.databinding.ActivitySplashBinding
import com.project.domagic.presentation.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {
    private val  binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    private val loginViewModel: LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            setSharedValueIsNotLogged()
            finish()
        }, 2000)
    }

    private fun setSharedValueIsNotLogged() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.putExtra("isNotLogged", isNotLogged())
        startActivity(intent)
    }

    private fun isNotLogged() = loginViewModel.isNotLogged()


}