package com.project.catalogingmtgcards.domain.mapper

import com.project.catalogingmtgcards.data.response.CardListResponseDto
import com.project.catalogingmtgcards.data.response.CardResponseDto
import com.project.catalogingmtgcards.domain.model.Card
import com.project.catalogingmtgcards.domain.model.CardModel
import com.project.catalogingmtgcards.domain.model.ListCardsModel

inline fun mapListResponseDto(input: CardListResponseDto,mapCardList: (List<CardResponseDto>?) -> List<CardModel>): ListCardsModel {
    return ListCardsModel(
        listCards = mapCardList(input.data)
    )
}

fun mapResponseDto(input: CardResponseDto): CardModel {
    return CardModel(
        name = input.name.orEmpty(),
        typeLine = input.typeLine.orEmpty(),
        manaCost = input.manaCost.orEmpty()
    )
}