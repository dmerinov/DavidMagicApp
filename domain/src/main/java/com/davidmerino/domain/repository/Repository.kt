package com.davidmerino.domain.repository

import com.davidmerino.domain.model.Card
import com.davidmerino.domain.model.LocalPrices
import io.reactivex.Single


interface Repository {
    fun getCards(): Single<List<Card>>
    fun getCardByID(id: String, success: (Card) -> Unit, error: () -> Unit)
    fun getBoosterPack(expansion: String, success: (List<Card>) -> Unit, error: () -> Unit)
    fun getCardMarketInfo(
        multiverseId: String,
        success: (LocalPrices) -> Unit,
        error: () -> Unit
    )
}