package com.project.catalogingmtgcards.data.repository.getImageManaSymbolRepository

import com.project.catalogingmtgcards.data.repository.state.StateSimbolRepository
import com.project.catalogingmtgcards.data.service.ScryfallService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.net.ConnectException


class SymbolRepositoryImpl(
    private val service: ScryfallService
) : SymbolRepository {

    override suspend fun getSymbolManaCost(): StateSimbolRepository {
        return withContext(Dispatchers.IO) {
            try {
                getListSvgSimbolManaCost()
            } catch (e: ConnectException) {
                StateSimbolRepository.Error(exception = Exception("Falha na comunicação com API"))
            } catch (e: Exception) {
                StateSimbolRepository.Error(exception = e)
            }
        }
    }

    private suspend fun getListSvgSimbolManaCost(): StateSimbolRepository {
        delay(4000)
        val response = service.getSymbologyManaCost()
        return if (response.isSuccessful) {
            StateSimbolRepository.Success(manaCost = response.body())
        } else {
            StateSimbolRepository.Error(exception = Exception("Falha ao buscar custo de mana"))
        }
    }
}