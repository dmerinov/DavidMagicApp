package com.davidmerino.data.mapper

import com.davidmerino.data.model.card.CardDto
import com.davidmerino.data.model.cardVO.CardVO
import com.davidmerino.domain.model.Card

fun CardDto.toCard() = Card(
    id = id,
    imageUrl = imageUrl
        ?: "https://upload.wikimedia.org/wikipedia/en/a/aa/Magic_the_gathering-card_back.jpg",
    manaCost = manaCost,
    multiverseid = multiverseid ?: "",
    name = name,
    set = set,
    text = text ?: "",
    toughness = toughness ?: "",
    power = power ?: ""
)

fun CardDto.toCardVO() = CardVO(
    id = id,
    imageUrl = imageUrl
        ?: "https://upload.wikimedia.org/wikipedia/en/a/aa/Magic_the_gathering-card_back.jpg",
    manaCost = manaCost,
    multiverseid = multiverseid ?: "",
    name = name,
    set = set,
    text = text ?: "",
    toughness = toughness ?: "",
    power = power ?: ""
)
fun CardVO.toCard() = Card(
    id = id,
    imageUrl = imageUrl
        ?: "https://upload.wikimedia.org/wikipedia/en/a/aa/Magic_the_gathering-card_back.jpg",
    manaCost = manaCost,
    multiverseid = multiverseid ?: "",
    name = name,
    set = set,
    text = text ?: "",
    toughness = toughness ?: "",
    power = power ?: ""
)
