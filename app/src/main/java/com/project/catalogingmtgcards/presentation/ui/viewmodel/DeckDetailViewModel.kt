package com.project.catalogingmtgcards.presentation.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.project.catalogingmtgcards.data.response.CardResponseDto
import com.project.catalogingmtgcards.domain.model.Card
import com.project.catalogingmtgcards.domain.useCase.getCardByNameRepository.GetCardByNameUseCase
import com.project.catalogingmtgcards.domain.useCase.getImageManaSymbolUseCase.GetSymbolManaCostUseCase
import com.project.catalogingmtgcards.domain.useCase.getUserDataUseCase.GetCardListDeckUseCase
import com.project.catalogingmtgcards.domain.useCase.state.ScryFallStateUseCase
import kotlinx.coroutines.launch

class DeckDetailViewModel(
    private val deckId: String,
    private val getCardByName: GetCardListDeckUseCase,
    private val useCaseListCard: GetCardByNameUseCase,
    private val useCaseSymbol: GetSymbolManaCostUseCase,
    application: Application
) : AndroidViewModel(application) {
    fun getListCardName(): LiveData<List<String>> {
        return getCardByName.getCardListDeck(deckId)
    }

    fun searchCardByName(cardName: String = "Adversária Sanguinária") {
        viewModelScope.launch {
            val getCard =
                useCaseListCard.getCardByName(cardName)
            if (getCard is ScryFallStateUseCase.Success) {
                val useCaseGetManaCostSymbol = getCard.cardNamed?.let {
                    useCaseSymbol.getSymbolManaCost(listOf(it))
                }
                if (useCaseGetManaCostSymbol is ScryFallStateUseCase.Success) {
                    val data = getCard.cardNamed
                    val mana = useCaseGetManaCostSymbol.symbologyMana?.get(0)
                    val card = createCardObject(data, mana)
                    Log.d("single card", card.toString())
                }

            }

        }
    }

    private fun createCardObject(
        card : CardResponseDto,
        manaCostList: List<String>?
    ): Card = Card(
            imgCard = card.imageCard.artCrop,
            name = card.name,
            typeLine = card.typeLine,
            listUrlManaCost = manaCostList
        )


}