package com.davidmerino.data.network

import com.davidmerino.data.model.card.CardDto

interface Network {
    fun getCards(success: (List<CardDto>) -> Unit, error: () -> Unit)
}