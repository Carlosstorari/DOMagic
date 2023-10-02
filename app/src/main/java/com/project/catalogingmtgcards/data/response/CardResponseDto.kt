package com.project.catalogingmtgcards.data.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class CardResponseDto(
    @SerializedName("name") val name: String,
    @SerializedName("type_line") val typeLine: String,
    @SerializedName("mana_cost") val manaCost: String,
)