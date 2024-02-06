package com.project.domagic.data.repository.autocompleteSearchRepository

import com.project.domagic.data.repository.state.StateCardRepository
import com.project.domagic.data.service.ScryfallService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.ConnectException

class AutocompleteSearchRepositoryImpl(private val service: ScryfallService):
    AutocompleteSearchRepository {
    override suspend fun getListCardAutocomplete(query: String): StateCardRepository {
        return withContext(Dispatchers.IO) {
            try {
                callCardNameList(query)
            } catch (e: ConnectException) {
                StateCardRepository.Error(exception = Exception("Falha na comunicação com API"))
            } catch (e: Exception) {
                StateCardRepository.Error(exception = e)
            }
        }
    }

    private suspend fun callCardNameList(cardName: String): StateCardRepository {
        val response = service.getListCardsAutoComplete(cardName)
        return if (response.isSuccessful) {
            StateCardRepository.Success(dataListCardNameAutocomplete = response.body())
        } else {
            StateCardRepository.Error(exception = Exception("Falha ao buscar card"))
        }
    }

}