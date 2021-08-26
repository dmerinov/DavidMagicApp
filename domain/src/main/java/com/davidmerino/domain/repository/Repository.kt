package com.davidmerino.domain.repository

import com.davidmerino.domain.model.Card
import com.davidmerino.domain.model.LocalPrice
import com.davidmerino.domain.model.Location
import io.reactivex.Single


interface Repository {
    fun getCards(): Single<List<Card>>
    fun getCardByID(id: String): Card
    fun getBoosterPack(expansion: String): Single<List<Card>>
    fun getCardMarketInfo(multiverseId: String): Single<LocalPrice>
    fun getShops(): Single<List<Location>>
}