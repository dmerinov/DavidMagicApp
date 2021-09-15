package com.davidmerino.domain.repository

import com.davidmerino.domain.Either
import com.davidmerino.domain.MagicError
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.model.LocalPrice
import com.davidmerino.domain.model.Shop
import io.reactivex.Completable
import io.reactivex.Single

interface Repository {
    suspend fun getCards(): Either<MagicError, List<Card>>
    fun getCardByID(id: String): Card
    suspend fun getBoosterPack(expansion: String): Either<MagicError, List<Card>>
    suspend fun getCardMarketInfo(multiverseId: String): Either<MagicError, LocalPrice>
    fun getShops(): Single<List<Shop>>
    fun setFavShop(shop: Shop): Completable
    fun setLifeCounter(player: Int, life: Int): Completable
    fun getLifeCounter(player: Int): Single<Int>
}