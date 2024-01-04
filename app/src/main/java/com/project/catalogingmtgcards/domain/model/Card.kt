package com.project.catalogingmtgcards.domain.model

data class Card(
    val imgCard:String?,
    val name: String?,
    val typeLine: String?,
    val listUrlManaCost : List<String>?
)