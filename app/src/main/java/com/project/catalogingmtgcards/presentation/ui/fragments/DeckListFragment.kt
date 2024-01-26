package com.project.catalogingmtgcards.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.catalogingmtgcards.NavGraphDirections
import com.project.catalogingmtgcards.databinding.FragmentDeckListBinding
import com.project.catalogingmtgcards.domain.model.DeckItem
import com.project.catalogingmtgcards.presentation.adapter.DeckListAdapter
import com.project.catalogingmtgcards.presentation.ui.fragments.ExtrasActivity.IS_NOT_LOGGED
import com.project.catalogingmtgcards.presentation.ui.viewmodel.DeckListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeckListFragment : Fragment() {
    private lateinit var binding: FragmentDeckListBinding
    private val viewModel: DeckListViewModel by viewModel()
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeckListBinding.inflate(inflater, container, false)
        val isNotLogged = requireActivity().intent.getBooleanExtra(IS_NOT_LOGGED, true)
        if (isNotLogged) goToLogin()
        setupViewModel()
        return binding.root
    }

    private fun setupViewModel() {
        viewModel.getListDecks().observe(viewLifecycleOwner) {
            it?.let { list ->
                setupRecyclerView(list)
            }
        }

        viewModel.getListCardDeckDetail(0).observe(viewLifecycleOwner) {
            it?.let { list ->
                Log.d("lista detalhe", list.toString())

            }
        }
    }

    private fun goToLogin() {
        val direction = NavGraphDirections.globalActionLogin()
        navController.navigate(direction)
    }

    private fun setupRecyclerView(deckList: List<DeckItem>) {
        binding.deckList.apply {
            adapter = DeckListAdapter(requireActivity(), deckList)
            layoutManager = GridLayoutManager(requireActivity(), 2)
        }
    }

}