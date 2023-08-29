package com.project.catalogingmtgcards.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Card(
    @SerialName("name") val name: String,
    @SerialName("type_line") val typeLine: String
)