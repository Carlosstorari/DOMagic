package com.project.catalogingmtgcards.domain.model

data class Card(
    val imgCard:String? = "https://cards.scryfall.io/art_crop/front/0/2/023b5e6f-10de-422d-8431-11f1fdeca246.jpg?1562895407",
    val name: String?,
    val typeLine: String? = "null",
    val listUrlManaCost : List<String>?
)