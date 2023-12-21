package com.project.catalogingmtgcards.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.project.catalogingmtgcards.R
import com.project.catalogingmtgcards.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_activity_nav_host) as NavHostFragment

        navController = navHostFragment.navController

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
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

}