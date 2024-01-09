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
    protected open var bottomNavigationViewVisibility = View.VISIBLE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isNotLogged()
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        if (requireActivity() is MainActivity) {
//            var  mainActivity = requireActivity() as MainActivity
//            mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
//        }
//    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity is MainActivity) {
            var  mainActivity = activity as MainActivity
            mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
        }
    }

    override fun onResume() {
        super.onResume()
        if (activity is MainActivity) {
            var  mainActivity = activity as MainActivity
            mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
        }
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