package com.project.catalogingmtgcards.data.service

import com.project.catalogingmtgcards.data.response.CardListResponse
import com.project.catalogingmtgcards.data.response.ListSymbols
import com.project.catalogingmtgcards.data.response.SymbologyManaCostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ScryfallService {
//    @GET("cards/named")
//    suspend fun getCardByName(@Query("fuzzy") cardName: String): Response<Card>

    @GET("cards/search")
    suspend fun getListCards(@Query("q") searchQuery: String): Response<CardListResponse>

    @GET("/symbology")
    suspend fun getSymbologyManaCost(): Response<ListSymbols>
}