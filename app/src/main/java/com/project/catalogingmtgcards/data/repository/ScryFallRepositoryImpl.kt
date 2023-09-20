package com.project.catalogingmtgcards.data.repository

import com.project.catalogingmtgcards.data.service.ScryfallService
import com.project.catalogingmtgcards.data.response.CardListResponse
import com.project.catalogingmtgcards.data.response.ListSymbols
import com.project.catalogingmtgcards.data.response.SymbologyManaCostResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.net.ConnectException


sealed class ScryFallStateRepository {
    data class Success(val data: CardListResponse?,
                       val manaCost: ListSymbols?) : ScryFallStateRepository()
    data class Error(val exception: Exception) : ScryFallStateRepository()
}

class ScryFallRepositoryImpl(
    private val service: ScryfallService
): ScryFallRepository {
    override suspend fun getListCardByColor(colorCardName: String): ScryFallStateRepository {
       return withContext(Dispatchers.IO) {
            try {
                callGetListCard(colorCardName)
            } catch (e: ConnectException) {
                ScryFallStateRepository.Error(exception = Exception("Falha na comunicação com API"))
            } catch (e: Exception) {
                ScryFallStateRepository.Error(exception = e)
            }
        }
    }

    override suspend fun getSymbolManaCost(): ScryFallStateRepository {
        return withContext(Dispatchers.IO) {
            try {
                callGetSymbolMana()
            } catch (e: ConnectException) {
                ScryFallStateRepository.Error(exception = Exception("Falha na comunicação com API"))
            } catch (e: Exception) {
                ScryFallStateRepository.Error(exception = e)
            }
        }
    }

    private suspend fun callGetListCard(
        cardName: String,
    ): ScryFallStateRepository {
        delay(4000)
        val response = service.getListCards(cardName)
        return if (response.isSuccessful) {
            ScryFallStateRepository.Success(data = response.body(), null)
        } else {
            ScryFallStateRepository.Error(exception = Exception("Falha ao buscar card"))
        }
    }

    private suspend fun callGetSymbolMana(): ScryFallStateRepository {
        delay(4000)
        val response = service.getSymbologyManaCost()
        return if (response.isSuccessful) {
            ScryFallStateRepository.Success(data = null, manaCost = response.body())
        } else {
            ScryFallStateRepository.Error(exception = Exception("Falha ao buscar custo de mana"))
        }
    }


}