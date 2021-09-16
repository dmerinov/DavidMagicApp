package com.davidmerino.data.datasource.local

import com.davidmerino.domain.Either
import com.davidmerino.domain.MagicError
import com.davidmerino.domain.model.Card

interface Local {
    fun hasCards(): Boolean
    fun getCards(): List<Card>
    fun setCards(cards: List<Card>)

    fun getCardByID(id: String): Either<MagicError, Card>
}