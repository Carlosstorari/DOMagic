package com.project.catalogingmtgcards.data.response

import kotlinx.serialization.SerialName

data class CardListResponseDto(
@SerialName("data") val data: List<CardResponseDto>
)