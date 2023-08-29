package com.project.catalogingmtgcards.domain.repository

import com.project.catalogingmtgcards.data.service.ScryfallService
import com.project.catalogingmtgcards.data.model.Card
import com.project.catalogingmtgcards.data.model.CardList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.ConnectException


sealed class Resultado<out R> {
    data class Sucesso<out T>(val dado: T?) : Resultado<T?>()
    data class Erro(val exception: Exception) : Resultado<Nothing>()
}

class ScryFallRepository(
    private val service: ScryfallService
) {
    suspend fun buscaCard(cardName: String): Resultado<CardList?> {
        var result: Resultado<CardList?> = Resultado.Erro(exception = Exception("Falha ao buscar card"))
        withContext(Dispatchers.Default) {
            try {
                kotlinx.coroutines.delay(4000)
                val resposta = service.getListCards(cardName)
                result = if (resposta.isSuccessful) {
                    Resultado.Sucesso(dado = resposta.body())
                } else {
                    Resultado.Erro(exception = Exception("Falha ao buscar card"))
                }
            } catch (e: ConnectException) {
                Resultado.Erro(exception = Exception("Falha na comunicação com API"))
            } catch (e: Exception) {
                Resultado.Erro(exception = e)
            }
        }
        return result
    }


}