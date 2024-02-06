package com.project.domagic.domain.model

data class Card(
    val imgCard:String?,
    val name: String?,
    val typeLine: String?,
    val listUrlManaCost : List<String>?
)