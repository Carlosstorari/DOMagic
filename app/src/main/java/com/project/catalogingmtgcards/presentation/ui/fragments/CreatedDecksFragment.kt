package com.project.catalogingmtgcards.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.catalogingmtgcards.R
import com.project.catalogingmtgcards.databinding.FragmentCreatedDecksBinding
import com.project.catalogingmtgcards.domain.model.DeckItem
import com.project.catalogingmtgcards.presentation.adapter.DeckListAdapter

class CreatedDecksFragment : BaseFragment() {
    private lateinit var binding: FragmentCreatedDecksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatedDecksBinding.inflate(inflater, container, false)

        val list = listOf(
            DeckItem(imgCard = null, name = "Izzet", subtitle = "azul vermelho"),
            DeckItem(imgCard = null, name = "Jundi", subtitle = "azul vermelho"),
            DeckItem(imgCard = null, name = "Smeagol", subtitle = "azul vermelho"),
            DeckItem(imgCard = null, name = "Frodo", subtitle = "azul vermelho")
        )
        setupRecyclerView(list)
        return binding.root
    }

    private fun setupRecyclerView(deckList: List<DeckItem>) {
        binding.deckList.apply {
            adapter = DeckListAdapter(requireActivity(), deckList)
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

}