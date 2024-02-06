package com.project.domagic.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.project.domagic.R
import com.project.domagic.ToLoginFlowDirections
import com.project.domagic.commons.snackBar
import com.project.domagic.databinding.FragmentLoginBinding
import com.project.domagic.domain.model.User
import com.project.domagic.presentation.ui.fragments.ExtrasActivity.IS_NOT_LOGGED
import com.project.domagic.presentation.ui.viewmodel.LoginViewModel
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
        setupLoginBtn()
        return binding.root
    }

    private fun authLogin(email: String, password: String) {
        loginViewModel.authLogin(User(email, password))
            .observe(viewLifecycleOwner, Observer {
                it?.let { resource ->
                    if (resource.content) {
                        requireActivity().intent.putExtra(IS_NOT_LOGGED, false)
                        val direction = ToLoginFlowDirections.actionLoginToDeckView()
                        navController.navigate(direction)
                    } else {
                        val errorMessage = resource.error?.let { error ->
                            getString(error)
                        } ?: getString(R.string.fail_auth)

                        view?.snackBar(errorMessage)

                    }
                }
            })
    }

    private fun setupLoginBtn() {
        binding.apply {
            loginBtn.setOnClickListener {
                cleanFields()
                val email = loginEmail.editText?.text.toString()
                val password = loginPassword.editText?.text.toString()
                if (validateFields(email, password)) {
                    authLogin(email, password)
                }
            }
        }
    }

    private fun FragmentLoginBinding.validateFields(email: String, password: String): Boolean {
        var isLoginValid = true

        if (email.isBlank()) {
            loginEmail.error = getString(R.string.email_required)
            isLoginValid = false
        }

        if (password.isBlank()) {
            loginPassword.error = getString(R.string.password_required)
        }
        return isLoginValid
    }

    private fun cleanFields() {
        binding.apply {
            loginEmail.error = null
            loginPassword.error = null
        }
    }

}