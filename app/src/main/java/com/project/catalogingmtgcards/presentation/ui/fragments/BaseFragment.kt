package com.project.catalogingmtgcards.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.project.catalogingmtgcards.NavGraphDirections
import com.project.catalogingmtgcards.presentation.ui.MainActivity
import com.project.catalogingmtgcards.presentation.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseFragment : Fragment() {
    private val loginViewModel: LoginViewModel by viewModel()
    private val navController by lazy { findNavController() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isNotLogged()
    }


    private fun isNotLogged() {
        if (loginViewModel.isNotLogged()) {
            goToLogin()
        }
    }

    private fun goToLogin() {
        val direction = NavGraphDirections.globalActionLogin()
        navController.navigate(direction)

    }

}