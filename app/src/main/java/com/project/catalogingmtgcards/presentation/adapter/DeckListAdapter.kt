package com.project.catalogingmtgcards.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.catalogingmtgcards.databinding.DeckItemBinding
import com.project.catalogingmtgcards.domain.model.DeckItem

class DeckListAdapter(
    private val context: Context,
    decks: List<DeckItem> = emptyList(),
    var onItemDeckClickListener: (deck: DeckItem) -> Unit = {}
) : RecyclerView.Adapter<DeckListAdapter.ViewHolder>() {

    private val decks = decks.toMutableList()

    inner class ViewHolder(
        private val binding: DeckItemBinding,
        private val context: Context,
    ) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var deckItem: DeckItem
        init {
            itemView.setOnClickListener {
                if(::deckItem.isInitialized) {
                    onItemDeckClickListener(deckItem)
                }
            }
        }
        fun bindDeck(deck: DeckItem) {
            this.deckItem = deck

            binding.apply {
                deckName.text = deck.name
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

    fun refresh(deckList: List<DeckItem>) {
        this.decks.clear()
        this.decks.addAll(deckList)
        notifyDataSetChanged()
    }
}