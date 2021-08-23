package com.davidmerino.data.mapper

import com.davidmerino.data.model.card.cardResponseMTG.CardDto
import com.davidmerino.data.model.card.cardResponseMTG.CardResponseDto
import com.davidmerino.data.model.card.cardResponseScryfall.Prices
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.model.LocalPrices

fun CardDto.toCard() = Card(
    id = id,
    imageUrl = imageUrl
        ?: "https://upload.wikimedia.org/wikipedia/en/a/aa/Magic_the_gathering-card_back.jpg",
    manaCost = manaCost ?: "",
    multiverseid = multiverseid ?: "",
    name = name,
    set = set,
    text = text ?: "",
    toughness = toughness ?: "",
    power = power ?: ""
)

fun Prices.toLocalPrices() = LocalPrices(
    eur = eur ?: "",
    eurFoil = eurFoil ?: "",
    tix = tix ?: "",
    usd = usd ?: "",
    usdFoil = usdFoil ?: ""
)

