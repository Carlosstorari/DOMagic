package com.project.catalogingmtgcards.domain.model

data class DeckItem(
    val id: String? = null,
    val imgCard: String?,
    val name: String?,
    val listCard: List<String>? = null
)