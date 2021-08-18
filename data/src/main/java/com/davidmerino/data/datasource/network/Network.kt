package com.davidmerino.data.datasource.network

import com.davidmerino.domain.model.Card
import com.davidmerino.domain.model.LocalPrices

interface Network {
    fun getCards(success: (List<Card>) -> Unit, error: () -> Unit)
    fun getCardBooster(expansion: String, success: (List<Card>) -> Unit, error: () -> Unit)
    fun getCardMarketInfo(multiverseId: String, success: (LocalPrices) -> Unit, error: () -> Unit)
}