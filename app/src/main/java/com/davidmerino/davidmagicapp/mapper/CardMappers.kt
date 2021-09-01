package com.davidmerino.davidmagicapp.mapper

import com.davidmerino.davidmagicapp.model.CardDetailView
import com.davidmerino.davidmagicapp.model.CardView
import com.davidmerino.domain.model.Card

fun Card.toCardDetailView() = CardDetailView(
    id, imageUrl, manaCost, name, set, text, toughness, power
)

fun Card.toCardView() = CardView(
    id = id,
    image = imageUrl,
    title = name,
    expansion = set,
    multiverseId = multiverseid
)