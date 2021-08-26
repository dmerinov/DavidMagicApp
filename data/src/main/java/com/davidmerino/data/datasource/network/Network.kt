package com.davidmerino.data.datasource.network

import com.davidmerino.domain.model.Card
import com.davidmerino.domain.model.LocalPrice
import com.davidmerino.domain.model.Location
import io.reactivex.Single

interface Network {
    fun getCards(): Single<List<Card>>
    fun getCardBooster(set: String): Single<List<Card>>
    fun getCardMarketInfo(multiverseId: String): Single<LocalPrice>
    fun getAllShops(): Single<List<Location>>
}