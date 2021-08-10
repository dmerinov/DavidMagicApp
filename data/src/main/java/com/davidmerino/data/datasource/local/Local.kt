package com.davidmerino.data.datasource.local

import com.davidmerino.data.model.card.CardDto
import com.davidmerino.data.model.cardVO.CardVO

interface Local {
    fun hasCards(): Boolean
    fun getCards(): List<CardVO>
    fun setCards(cards: List<CardDto>)
}