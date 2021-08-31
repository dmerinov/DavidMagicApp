package com.davidmerino.domain.repository

import com.davidmerino.domain.model.Card
import com.davidmerino.domain.model.LocalPrice
import com.davidmerino.domain.model.Shop
import io.reactivex.Completable
import io.reactivex.Single

interface Repository {
    fun getCards(): Single<List<Card>>
    fun getCardByID(id: String): Card
    fun getBoosterPack(expansion: String): Single<List<Card>>
    fun getCardMarketInfo(multiverseId: String): Single<LocalPrice>
    fun getShops(): Single<List<Shop>>
    fun setFavShop(shop: Shop): Completable
}