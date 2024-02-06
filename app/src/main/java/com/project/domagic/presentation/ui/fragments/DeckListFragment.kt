package com.project.domagic.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.project.domagic.NavGraphDirections
import com.project.domagic.databinding.FragmentDeckListBinding
import com.project.domagic.presentation.adapter.DeckListAdapter
import com.project.domagic.presentation.ui.fragments.ExtrasActivity.IS_NOT_LOGGED
import com.project.domagic.presentation.ui.viewmodel.DeckListViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeckListFragment : Fragment() {
    private lateinit var binding: FragmentDeckListBinding
    private val viewModel: DeckListViewModel by viewModel()
    private val navController by lazy { findNavController() }
    private val adapter: DeckListAdapter by inject()

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
                adapter.refresh(list)
                adapter.onItemDeckClickListener = { selectedDeck ->
                    selectedDeck.deckId?.let { id ->
                        val directions =
                            DeckListFragmentDirections.actionCreatedDecksFragmentToDeckDetailFragment(id)
                        navController.navigate(directions)
                    }
                }
                setupRecyclerView()
            }
        }
    }

    private fun goToLogin() {
        val direction = NavGraphDirections.globalActionLogin()
        navController.navigate(direction)
    }

    private fun setupRecyclerView() {
        binding.deckList.adapter = adapter
        binding.deckList.layoutManager = GridLayoutManager(requireActivity(), 2)
    }

}