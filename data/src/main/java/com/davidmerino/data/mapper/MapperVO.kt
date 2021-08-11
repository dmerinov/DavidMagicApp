package com.davidmerino.data.mapper

import com.davidmerino.data.model.cardVO.CardVO
import com.davidmerino.domain.model.Card

fun CardVO.toCard() = Card(
    id = id,
    imageUrl = imageUrl,
    manaCost = manaCost,
    multiverseid = multiverseid ?: "",
    name = name,
    set = set,
    text = text ?: "",
    toughness = toughness ?: "",
    power = power ?: ""
)

fun Card.toCardVO() = CardVO(

)