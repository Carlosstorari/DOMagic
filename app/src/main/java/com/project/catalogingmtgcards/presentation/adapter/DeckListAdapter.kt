package com.project.catalogingmtgcards.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.catalogingmtgcards.databinding.DeckItemBinding
import com.project.catalogingmtgcards.domain.model.DeckItem

class DeckListAdapter(
    private val context: Context,
    decks: List<DeckItem>
) : RecyclerView.Adapter<DeckListAdapter.ViewHolder>() {

    private val decks = decks.toMutableList()

    class ViewHolder(
        private val binding: DeckItemBinding,
        private val context: Context,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindDeck(deck: DeckItem) {
            binding.apply {
                deckName.text = deck.name
                deckSubtitle.text = deck.subtitle
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = DeckItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, context)
    }

    override fun getItemCount(): Int = decks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val deck = decks[position]
        holder.bindDeck(deck)
    }
}