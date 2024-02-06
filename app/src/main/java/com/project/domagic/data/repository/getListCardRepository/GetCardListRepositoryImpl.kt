package com.project.domagic.data.repository.getListCardRepository

import com.project.domagic.data.repository.state.StateCardRepository
import com.project.domagic.data.service.ScryfallService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.ConnectException

class GetCardListRepositoryImpl(
    private val service: ScryfallService
) : GetCardListRepository {

    override suspend fun getListCardByColor(colorCardName: String): StateCardRepository {
        return withContext(Dispatchers.IO) {
            try {
                callGetListCard(colorCardName)
            } catch (e: ConnectException) {
                StateCardRepository.Error(exception = Exception("Falha na comunicação com API"))
            } catch (e: Exception) {
                StateCardRepository.Error(exception = e)
            }
        }
    }

    private suspend fun callGetListCard(cardName: String): StateCardRepository {
        val response = service.getListCards(cardName)
        return if (response.isSuccessful) {
            StateCardRepository.Success(dataListCard = response.body())
        } else {
            StateCardRepository.Error(exception = Exception("Falha ao buscar card"))
        }
    }
}