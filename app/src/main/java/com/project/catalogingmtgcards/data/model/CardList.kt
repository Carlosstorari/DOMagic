package com.project.catalogingmtgcards.data.model

import kotlinx.serialization.SerialName

data class CardList(
@SerialName("data") val data: List<Card>
)