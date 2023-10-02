package com.project.catalogingmtgcards.data.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ListSymbolsResponseDto (
    @SerializedName("data") val dataSymbolsList: List<SymbologyManaCostResponseDto>
    )