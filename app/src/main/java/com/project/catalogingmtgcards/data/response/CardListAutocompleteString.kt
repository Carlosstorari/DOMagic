package com.project.catalogingmtgcards.data.response

import kotlinx.serialization.SerialName

data class CardListAutocompleteString(
    @SerialName("data") val data: List<String>
)