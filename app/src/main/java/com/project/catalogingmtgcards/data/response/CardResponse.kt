package com.project.catalogingmtgcards.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CardResponse(
    @SerialName("name") val name: String,
    @SerialName("type_line") val typeLine: String
)