package com.project.catalogingmtgcards.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.project.catalogingmtgcards.R
import com.project.catalogingmtgcards.databinding.ActivityMainBinding
import com.project.catalogingmtgcards.presentation.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_activity_nav_host) as NavHostFragment

        navController = navHostFragment.navController

        binding.bottomNavigation.visibility = View.GONE
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.search_page -> {
                    navController.navigate(R.id.action_createdDecksFragment_to_searchCardFragment)
                    true
                }

                R.id.deck_page -> {
                    navController.navigate(R.id.action_searchCardFragment_to_createdDecksFragment)
                    true
                }

                else -> false
            }
        }

    }

    fun setBottomNavigationVisibility(visibility: Int) {
        binding.bottomNavigation.visibility = visibility
    }

}