package com.davidmerino.data.datasource.network

import com.davidmerino.domain.model.Card

interface Network {
    fun getCards(success: (List<Card>) -> Unit, error: () -> Unit)
}