package com.project.domagic.presentation.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.domagic.data.response.CardResponseDto
import com.project.domagic.domain.useCase.state.ScryFallStateUseCase
import com.project.domagic.domain.model.Card
import com.project.domagic.domain.useCase.getListCardUseCase.GetListCardUseCase
import com.project.domagic.domain.useCase.getCardByNameRepository.GetCardByNameUseCase
import com.project.domagic.domain.useCase.autocompleteSearchUseCase.GetListNameCardAutocompleteUseCase
import com.project.domagic.domain.useCase.getImageManaSymbolUseCase.GetSymbolManaCostUseCase
import com.project.domagic.presentation.ui.fragments.SearchConstants.ONLY_RED
import kotlinx.coroutines.launch

class SearchCardViewModel(
    application: Application,
    private val useCaseListCard: GetListCardUseCase,
    private val useCaseSymbol: GetSymbolManaCostUseCase,
    private val useCaseAutocomplete: GetListNameCardAutocompleteUseCase,
    private val useCaseCardNamed: GetCardByNameUseCase
) : AndroidViewModel(application) {
    private val state: MutableLiveData<ScryFallViewModelState> = MutableLiveData()
    val getState: LiveData<ScryFallViewModelState> = state

    fun getCardListItem(searchQuery: String = ONLY_RED) {
        viewModelScope.launch {
            val useCaseGetCardsList = useCaseListCard.getListCardUseCase(searchQuery)
            state.postValue(ScryFallViewModelState.Loading)
            if (useCaseGetCardsList is ScryFallStateUseCase.Success) {
                val useCaseGetManaCostSymbol =
                    useCaseGetCardsList.listCard?.let { useCaseSymbol.getSymbolManaCost(it.data) }
                if (useCaseGetManaCostSymbol is ScryFallStateUseCase.Success) {
                    state.postValue(
                        ScryFallViewModelState.Success(
                            createListCard(
                                useCaseGetCardsList.listCard.data,
                                useCaseGetManaCostSymbol.symbologyMana
                            )
                        )
                    )
                }
            }
        }
    }

    fun getCardListItemAutocomplete(query: String?) {
        viewModelScope.launch {
            val useCaseAutocomplete = query?.let { useCaseAutocomplete.getListAutocomplete(it) }
            if (useCaseAutocomplete is ScryFallStateUseCase.Success) {
                val listAutocomplete = getCardNameToCreateObject(useCaseAutocomplete)
                val useCaseGetManaCostSymbol =
                    listAutocomplete?.let { useCaseSymbol.getSymbolManaCost(it) }
                if (useCaseGetManaCostSymbol is ScryFallStateUseCase.Success) {
                    state.postValue(
                        ScryFallViewModelState.Success(
                            createListCard(
                                listAutocomplete,
                                useCaseGetManaCostSymbol.symbologyMana
                            )
                        )
                    )
                }
            }
        }
    }

    private suspend fun getCardNameToCreateObject(
        useCaseAutocomplete: ScryFallStateUseCase.Success
    ): MutableList<CardResponseDto> {
        var listAutocomplete: MutableList<CardResponseDto> = mutableListOf()
        useCaseAutocomplete.listNameAutocomplete?.data?.forEach { name ->
            val useCaseCardNamed = useCaseCardNamed.getCardByName(name)
            state.postValue(ScryFallViewModelState.Loading)
            if (useCaseCardNamed is ScryFallStateUseCase.Success) {
                useCaseCardNamed.cardNamed?.let { listAutocomplete.add(it) }
            }
        }
        return listAutocomplete
    }

    private fun createListCard(
        listCard: List<CardResponseDto>,
        manaCostList: List<List<String>>?
    ): List<Card> {
        var cardList = mutableListOf<Card>()
        listCard.forEachIndexed { index, cardResponse ->
            cardResponse.imageCard?.let {
                cardList.add(
                    Card(
                        imgCard = it.artCrop,
                        name = cardResponse.name,
                        typeLine = cardResponse.typeLine,
                        listUrlManaCost = manaCostList?.get(index)
                    )
                )
            }

        }
        return cardList
    }
}

