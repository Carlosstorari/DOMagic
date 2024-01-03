package com.project.catalogingmtgcards.data.service

import com.project.catalogingmtgcards.data.response.CardListAutocompleteString
import com.project.catalogingmtgcards.data.response.CardListResponseDto
import com.project.catalogingmtgcards.data.response.CardResponseDto
import com.project.catalogingmtgcards.data.response.ListSymbolsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ScryfallService {
    @GET("cards/search")
    suspend fun getListCards(
        @Query("q") searchQuery: String
    ): Response<CardListResponseDto>

    @GET("/symbology")
    suspend fun getSymbologyManaCost(): Response<ListSymbolsResponseDto>

    @GET("cards/autocomplete")
    suspend fun getListCardsAutoComplete(
        @Query("q") searchQuery: String
    ): Response<CardListAutocompleteString>

    @GET("cards/named")
    suspend fun getCardByName(
        @Query("fuzzy") searchQuery: String
    ): Response<CardResponseDto>
}