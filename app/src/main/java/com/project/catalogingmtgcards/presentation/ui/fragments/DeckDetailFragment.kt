package com.project.catalogingmtgcards.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.catalogingmtgcards.R
import com.project.catalogingmtgcards.databinding.FragmentDeckDetailBinding
import com.project.catalogingmtgcards.domain.model.Card
import com.project.catalogingmtgcards.presentation.adapter.ListCardsAdapter
import com.project.catalogingmtgcards.presentation.ui.MainActivity
import com.project.catalogingmtgcards.presentation.ui.viewmodel.DeckDetailViewModel
import com.project.catalogingmtgcards.presentation.ui.viewmodel.ScryFallViewModelState
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DeckDetailFragment : Fragment() {
    lateinit var binding: FragmentDeckDetailBinding
    private val navController by lazy {
        findNavController()
    }
    private val args by navArgs<DeckDetailFragmentArgs>()
    private val deckId by lazy {
        args.deckId
    }
    private val viewModel: DeckDetailViewModel by viewModel{ parametersOf(deckId) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupBindView(inflater, container)
        setupViewModels()
        (requireActivity() as MainActivity).changeToolbarTitle("teste deck")
        return binding.root
    }

    private fun setupBindView(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentDeckDetailBinding.inflate(inflater, container, false)
    }

    private fun setupViewModels() {
        setupViewModelGetListCardName()
        setupViewModelState()
    }

    private fun setupViewModelGetListCardName() {
        viewModel.getListCardName().observe(viewLifecycleOwner) {
            it?.let { list ->
                binding.shimmerDeckDetail.createCardViewShimmer(list.size)
                viewModel.searchCardByName(list)
            }
        }
    }

    private fun setupViewModelState() {
        viewModel.getState.observe(viewLifecycleOwner) {
            when (it) {
                is ScryFallViewModelState.Success -> {
                    setupRecyclerView(it.card)
                    setupViewVisibilityStateSuccess()
                }

                is ScryFallViewModelState.Loading -> {
                    setupViewVisibilityStateLoading()
                }

                else -> {
                    Log.d("e", "Errorrrrr")
                }
            }
        }
    }

    private fun setupViewVisibilityStateLoading() {
        binding.shimmerDeckDetail.visibility = View.VISIBLE
        binding.cardListDeckDetail.visibility = View.GONE
    }

    private fun setupViewVisibilityStateSuccess() {
        binding.shimmerDeckDetail.visibility = View.GONE
        binding.cardListDeckDetail.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(cardList: List<Card>) {
        binding.cardListDeckDetail.apply {
            adapter = ListCardsAdapter(requireActivity(), cardList)
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }


}