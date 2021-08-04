package com.davidmerino.data.mapper

import com.davidmerino.data.model.card.CardDto
import com.davidmerino.domain.model.Card

class Mappers {

    fun CardDto.toCard() = Card(
        id, imageUrl, manaCost, name, set, text, toughness, power
    )

}