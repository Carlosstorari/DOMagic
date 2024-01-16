package com.project.catalogingmtgcards.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.project.catalogingmtgcards.R
import com.project.catalogingmtgcards.core.DataCoordinator
import com.project.catalogingmtgcards.core.getStringDataStore
import com.project.catalogingmtgcards.databinding.ActivityMainBinding
import com.project.catalogingmtgcards.presentation.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController
    private val loginViewModel: LoginViewModel by viewModel()
    var uid = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_activity_nav_host) as NavHostFragment

        navController = navHostFragment.navController
        setupBottomNav()
        setupVisibilityBottomNav()
        setupCoordinators()


    }

    private fun setupBottomNav() {
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

    private fun setupVisibilityBottomNav() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.search_card_fragment -> showBottomNav()
                R.id.created_decks_fragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.bottomNavigation.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        binding.bottomNavigation.visibility = View.GONE

    }

    private fun setupCoordinators() {
        DataCoordinator.shared.initialize(
            context = baseContext,
            onLoad = {
                GlobalScope.launch {
                    uid = DataCoordinator.shared.getStringDataStore()
                }
            }
        )
    }
}