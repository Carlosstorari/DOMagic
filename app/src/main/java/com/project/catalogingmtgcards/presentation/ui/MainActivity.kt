package com.project.catalogingmtgcards.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.project.catalogingmtgcards.R
import com.project.catalogingmtgcards.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_activity_nav_host) as NavHostFragment
        navController = navHostFragment.navController
        setupBottomNav()
        setupVisibilityBottomNav()
        //hideSystemUI()
        //setHasOptionsMenu(true)
    }

    //    private fun hideSystemUI() {
//        WindowCompat.setDecorFitsSystemWindows(window, false)
//        WindowInsetsControllerCompat(window, binding.root).let { controller ->
//            controller.hide(WindowInsetsCompat.Type.statusBars())
//            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//        }
//    }
    fun changeToolbarTitle(title: String) {
        binding.titleToolbar.text = title
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
                R.id.search_card_fragment -> {
                    showBottomNav()
                    binding.titleToolbar.text = "buscar card"
                }

                R.id.created_decks_fragment -> {
                    showBottomNav()
                    binding.titleToolbar.text = "decks"
                }

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
}