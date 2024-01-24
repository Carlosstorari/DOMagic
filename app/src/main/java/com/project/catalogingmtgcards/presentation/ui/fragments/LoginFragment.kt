package com.project.catalogingmtgcards.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.project.catalogingmtgcards.R
import com.project.catalogingmtgcards.ToLoginFlowDirections
import com.project.catalogingmtgcards.databinding.FragmentLoginBinding
import com.project.catalogingmtgcards.domain.model.User
import com.project.catalogingmtgcards.presentation.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val navController by lazy {
        findNavController()
    }
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val email = binding.loginEmail.editText?.text
        val password = binding.loginPassword.editText?.text
        binding.loginBtn.setOnClickListener {
            authLogin(email.toString(), password.toString())
        }
        return binding.root
    }

//    fun changeGraphToRoot(fragment: Fragment): NavController {
//        val navController = fragment.findNavController()
//        navController.setGraph(R.navigation.nav_root)
//        return navController
//    }

    private fun authLogin(email: String, password: String) {
        loginViewModel.authLogin(User(email, password))
            .observe(viewLifecycleOwner, Observer {
                it?.let { resource ->
                    if (resource.content) {
                        requireActivity().intent.putExtra("isNotLogged", false)
                        val direction = ToLoginFlowDirections.actionGlobalSearchView()
                        navController.navigate(direction)
                    } else {
                        val errorMessage = resource.error?.let { error ->
                            getString(error)
                        } ?: getString(R.string.fail_auth)

                    }
                }
            })
    }

}