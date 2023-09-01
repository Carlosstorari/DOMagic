package com.project.catalogingmtgcards.data.response

import kotlinx.serialization.SerialName

data class CardListResponse(
@SerialName("data") val data: List<CardResponse>
)