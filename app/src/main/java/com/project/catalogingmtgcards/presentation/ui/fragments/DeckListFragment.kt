package com.project.catalogingmtgcards.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.catalogingmtgcards.databinding.FragmentDeckListBinding
import com.project.catalogingmtgcards.domain.model.DeckItem
import com.project.catalogingmtgcards.presentation.adapter.DeckListAdapter
import com.project.catalogingmtgcards.presentation.ui.viewmodel.DeckListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeckListFragment : BaseFragment() {
    private lateinit var binding: FragmentDeckListBinding
    private val viewModel: DeckListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeckListBinding.inflate(inflater, container, false)

        viewModel.getListDecks().observe(viewLifecycleOwner) {
            it?.let { list ->
                setupRecyclerView(list)
            }
        }
        return binding.root
    }

    private fun setupRecyclerView(deckList: List<DeckItem>) {
        binding.deckList.apply {
            adapter = DeckListAdapter(requireActivity(), deckList)
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

}