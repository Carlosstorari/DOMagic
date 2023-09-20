package com.project.catalogingmtgcards.presentation.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.catalogingmtgcards.data.response.CardListResponse
import com.project.catalogingmtgcards.data.response.CardResponse
import com.project.catalogingmtgcards.domain.ScryFallStateUseCase
import com.project.catalogingmtgcards.domain.model.Card
import com.project.catalogingmtgcards.domain.useCase.GetCardByColorUseCase
import com.project.catalogingmtgcards.domain.useCase.GetSymbolManaCostUseCase
import kotlinx.coroutines.launch

class ScryFallViewModel(
    application: Application,
    private val useCase: GetCardByColorUseCase,
    private val useCaseSymbol: GetSymbolManaCostUseCase
    ): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    private val state: MutableLiveData<ScryFallViewModelState> = MutableLiveData()
    val getState: LiveData<ScryFallViewModelState> = state


    fun getCardListItem() {
        viewModelScope.launch {
            val useCaseGetCardsList = useCase.getListCardByColorUseCase("color:red cmc=3")
            if (useCaseGetCardsList is ScryFallStateUseCase.Success) {
                val useCaseGetManaCostSymbol = useCaseGetCardsList.card?.let { useCaseSymbol.getSymbolManaCost(it.data) }
                if (useCaseGetManaCostSymbol is ScryFallStateUseCase.Success){
                    state.postValue(ScryFallViewModelState.Success(createListCard(useCaseGetCardsList.card.data, useCaseGetManaCostSymbol.symbologyMana)))
                }
            }
        }
    }

    private fun createListCard(listCard: List<CardResponse>, manaCostList: List<List<String>>?): List<Card>{
        var cardList = mutableListOf<Card>()

        listCard.forEachIndexed { index, cardResponse ->
            cardList.add(Card(
                name = cardResponse.name,
                typeLine = cardResponse.typeLine,
                listUrlManaCost = manaCostList?.get(index)
            ))

        }
        return cardList
    }
}

