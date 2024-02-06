package com.project.domagic.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.domagic.databinding.FragmentNewUserRegisterBinding

class NewUserRegister : Fragment() {

    private lateinit var binding: FragmentNewUserRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewUserRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

}