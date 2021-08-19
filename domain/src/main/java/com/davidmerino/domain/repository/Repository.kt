package com.davidmerino.domain.repository

import com.davidmerino.domain.model.Card
import com.davidmerino.domain.model.LocalPrices


interface Repository {
    fun getCards(success: (List<Card>) -> Unit, error: () -> Unit)
    fun getCardByID(id: String, success: (Card) -> Unit, error: () -> Unit)
    fun getBoosterPack(expansion: String, success: (List<Card>) -> Unit, error: () -> Unit)
    fun getCardMarketInfo(
        multiverseId: String,
        success: (LocalPrices) -> Unit,
        error: () -> Unit
    )
}