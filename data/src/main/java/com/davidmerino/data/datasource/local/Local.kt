package com.davidmerino.data.datasource.local

import com.davidmerino.data.model.card.CardDto
import com.davidmerino.domain.model.Card

interface Local {
    fun hasCards(): Boolean
    fun getCards(): List<Card>
    fun setCards(cards: List<CardDto>)
}