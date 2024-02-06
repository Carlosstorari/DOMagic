package com.project.domagic.data.response

import kotlinx.serialization.SerialName

data class CardListResponseDto(
@SerialName("data") val data: List<CardResponseDto>
)