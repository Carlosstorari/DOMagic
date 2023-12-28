package com.project.catalogingmtgcards.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.catalogingmtgcards.databinding.FragmentSearchCardBinding
import com.project.catalogingmtgcards.domain.model.Card
import com.project.catalogingmtgcards.presentation.adapter.ListaCardsAdapter
import com.project.catalogingmtgcards.presentation.ui.viewmodel.ScryFallViewModel
import com.project.catalogingmtgcards.presentation.ui.viewmodel.ScryFallViewModelState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCardFragment : Fragment() {
    private lateinit var binding: FragmentSearchCardBinding
    private val viewModel: ScryFallViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchCardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCardListItem()
        binding.openPopUp.setOnClickListener {
            DialogFilterColor(requireActivity()).showDialog { query ->
                viewModel.getCardListItem(query)
                binding.shimmerSearchView.visibility = View.VISIBLE
                binding.cardListHome.visibility = View.GONE
            }
        }
        viewModel.getState.observe(viewLifecycleOwner) {
            when (it) {
                is ScryFallViewModelState.Success -> {
                    setupRecyclerView(it.card)
                    binding.shimmerSearchView.visibility = View.GONE
                    binding.cardListHome.visibility = View.VISIBLE
                }
                is ScryFallViewModelState.Loading -> {
                    binding.shimmerSearchView.visibility = View.VISIBLE
                }

                else -> {
                    Log.d("e", "Errorrrrr")
                }
            }
        }
    }

    private fun setupRecyclerView(list: List<Card>) {
        binding.cardListHome.apply {
            adapter = ListaCardsAdapter(requireActivity(), list)
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

}