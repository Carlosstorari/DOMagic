package com.project.catalogingmtgcards.domain.model

data class Card(
    val name: String?,
    val typeLine: String? = "null",
    val listUrlManaCost : List<String>?
)