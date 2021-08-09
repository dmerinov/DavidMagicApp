package com.davidmerino.davidmagicapp.mapper

import com.davidmerino.davidmagicapp.model.CardDetailView
import com.davidmerino.davidmagicapp.model.CardView
import com.davidmerino.domain.model.Card

fun Card.toCardDetailView() = toughness?.let {
    power?.let { it1 ->
        CardDetailView(
        id, imageUrl, manaCost, name, set, text, it, it1
    )
    }
}

fun Card.toCardView() = CardView(
    image = imageUrl,
    title = name,
    expansion = set
)