package com.project.catalogingmtgcards.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
        viewModel.getCardListItem()
        viewModel.getState.observe(this) {
            when (it) {
                is ScryFallViewModelState.Success -> {
                    setupRecyclerView(it.card)
                    Log.d("cardList", it.card.toString())
                }

                else -> {
                    Log.d("e", "Errorrrrr")
                }
            }
        }
    }

    private fun setupRecyclerView(list: List<Card>) {
        binding.cardListHome.apply {
            adapter = ListaCardsAdapter(this@MainActivity, list)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

}