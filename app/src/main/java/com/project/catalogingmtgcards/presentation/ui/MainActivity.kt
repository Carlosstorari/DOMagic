package com.project.catalogingmtgcards.presentation.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.catalogingmtgcards.data.response.CardResponse
import com.project.catalogingmtgcards.databinding.ActivityMainBinding
import com.project.catalogingmtgcards.domain.model.Card
import com.project.catalogingmtgcards.presentation.adapter.ListaCardsAdapter
import com.project.catalogingmtgcards.presentation.ui.viewmodel.ScryFallViewModel
import com.project.catalogingmtgcards.presentation.ui.viewmodel.ScryFallViewModelState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: ScryFallViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.searchCard()
        viewModel.getState.observe(this, Observer {
            when(it) {
                is ScryFallViewModelState.Success -> {
                    setupRecyclerView(it.card.data)
                }
            }
        })
    }

    private fun setupRecyclerView(list: List<CardResponse>) {
        binding.cardListHome.apply {
            adapter = ListaCardsAdapter(this@MainActivity, list)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

}