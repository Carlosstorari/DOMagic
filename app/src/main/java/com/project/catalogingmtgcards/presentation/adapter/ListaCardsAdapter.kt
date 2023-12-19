package com.project.catalogingmtgcards.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.catalogingmtgcards.databinding.CardListItemBinding
import com.project.catalogingmtgcards.domain.model.Card


class ListaCardsAdapter(
    private val context: Context,
    cards: List<Card>
) : RecyclerView.Adapter<ListaCardsAdapter.ViewHolder>() {

    private val cards = cards.toMutableList()
    private var idListCard = mutableSetOf<String>()
    class ViewHolder(
        private val binding: CardListItemBinding,
        private val context: Context,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindCard(card: Card, idListCard: MutableSet<String>) {
            binding.apply {
                cardIv.setUri(card.imgCard)
                cardName.text = card.name
                cardType.text = card.typeLine
                val verify = manaIvContainer.verifyCardId(idListCard, card.name.toString()  )
                if (verify){
                    manaIvContainer.setUriList(card.listUrlManaCost)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = CardListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, context)
    }

    override fun getItemCount(): Int = cards.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.bindCard(card, idListCard)
        idListCard.add(card.name.toString())
    }
}