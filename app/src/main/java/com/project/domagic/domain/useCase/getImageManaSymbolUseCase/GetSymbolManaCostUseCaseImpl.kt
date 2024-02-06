package com.project.domagic.domain.useCase.getImageManaSymbolUseCase

import android.util.Log
import com.project.domagic.data.repository.getImageManaSymbolRepository.SymbolRepository
import com.project.domagic.data.repository.state.StateSimbolRepository
import com.project.domagic.data.response.CardResponseDto
import com.project.domagic.data.response.SymbologyManaCostResponseDto
import com.project.domagic.data.service.ScryfallService
import com.project.domagic.domain.useCase.state.ScryFallStateUseCase

class GetSymbolManaCostUseCaseImpl(
    private val repository: SymbolRepository,
    private val service: ScryfallService
) : GetSymbolManaCostUseCase {

    override suspend fun getSymbolManaCost(symbolsManaCost: List<CardResponseDto>): ScryFallStateUseCase {
        val responseSymbols = service.getSymbologyManaCost()
        return when (repository.getSymbolManaCost()) {
            StateSimbolRepository.Success(
                manaCost = responseSymbols.body()
            ) -> {
                val mapUri = responseSymbols.body()?.let { mapSymbolWithUri(it.dataSymbolsList) }

                ScryFallStateUseCase.Success(
                    symbologyMana = mapUri?.let { mapManaCost ->
                        createListUri(
                            symbolsManaCost,
                            mapManaCost
                        )
                    })
            }

            else -> {
                ScryFallStateUseCase.Empty
            }
        }
    }

    private fun mapSymbolWithUri(listSymbols: List<SymbologyManaCostResponseDto>): Map<String, String> {
        val map = mutableMapOf<String, String>()
        for (item in listSymbols) {
            map.getOrPut(item.symbol) { item.svgUri }
        }
        return map
    }

    private fun createListUri(
        listCards: List<CardResponseDto>,
        mapManaCostUrl: Map<String, String>
    ): List<List<String>> {
        var manaCostListPerCard = mutableListOf<String>()
        var cardsListMana = mutableListOf<List<String>>()
        for (manaCostPerCard in listCards) {
            var listManaTypeOfCard = createManaCostListPerCard(manaCostPerCard.manaCost)
            if (listManaTypeOfCard != null) {
                for (mana in listManaTypeOfCard) {
                    mapManaCostUrl.forEach { entry ->
                        if ("{$mana}".contains(entry.key)) {
                            manaCostListPerCard.add(entry.value)
                        }
                    }
                }
            }
            cardsListMana.add(manaCostListPerCard)
            manaCostListPerCard = mutableListOf<String>()
        }
        return cardsListMana
    }

    private fun createManaCostListPerCard(manaCost: String?): List<String>? {
        Log.d(
            "createManaCostLi",
            manaCost?.split("{", "}")?.filter { it != "" && it != null }.toString()
        )
        return manaCost?.split("{", "}")?.filter { it != "" && it != null }
    }

}