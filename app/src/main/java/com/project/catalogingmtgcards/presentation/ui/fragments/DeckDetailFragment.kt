package com.project.catalogingmtgcards.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.catalogingmtgcards.databinding.FragmentDeckDetailBinding
import com.project.catalogingmtgcards.presentation.adapter.ListCardsAdapter
import com.project.catalogingmtgcards.presentation.ui.viewmodel.DeckDetailViewModel
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
        binding = FragmentDeckDetailBinding.inflate(inflater, container, false)

        viewModel.getListCardName().observe(viewLifecycleOwner) {
            it?.let { list ->
                Log.d("lista detalhe", list.toString())
                viewModel.searchCardByName(list).observe(viewLifecycleOwner) { cardList ->
                    Log.d("card list", cardList.toString())
                    binding.cardListDeckDetail.apply {
                        adapter = ListCardsAdapter(requireActivity(), cardList)
                        layoutManager = LinearLayoutManager(requireActivity())
                    }
                }
            }
        }

        return binding.root
    }



}