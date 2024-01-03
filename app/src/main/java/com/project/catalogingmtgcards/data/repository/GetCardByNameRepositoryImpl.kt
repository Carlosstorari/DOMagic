package com.project.catalogingmtgcards.data.repository

import com.project.catalogingmtgcards.data.service.ScryfallService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.ConnectException

class GetCardByNameRepositoryImpl(
    private val service: ScryfallService
) :GetCardByNameRepository {
    override suspend fun getCardByName(name: String): StateCardRepository {
        return withContext(Dispatchers.IO) {
            try {
                callGetListCard(name)
            } catch (e: ConnectException) {
                StateCardRepository.Error(exception = Exception("Falha na comunicação com API"))
            } catch (e: Exception) {
                StateCardRepository.Error(exception = e)
            }
        }
    }

    private suspend fun callGetListCard(cardName: String): StateCardRepository {
        val response = service.getCardByName(cardName)
        return if (response.isSuccessful) {
            StateCardRepository.Success(dataNamedCard = response.body())
        } else {
            StateCardRepository.Error(exception = Exception("Falha ao buscar card"))
        }
    }
}