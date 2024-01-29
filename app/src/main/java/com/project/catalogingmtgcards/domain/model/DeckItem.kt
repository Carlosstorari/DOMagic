package com.project.catalogingmtgcards.domain.model

data class DeckItem(
    val userId: String? = null,
    val deckId: String? = null,
    val imgCard: String?,
    val name: String?,
    val listCard: List<String>? = null
)