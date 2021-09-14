package com.davidmerino.data.datasource.network

import com.davidmerino.domain.Either
import com.davidmerino.domain.MagicError
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.model.LocalPrice
import com.davidmerino.domain.model.Shop
import io.reactivex.Single

interface Network {
    suspend fun getCards(): Either<MagicError, List<Card>>
    fun getCardBooster(set: String): Single<List<Card>>
    fun getCardMarketInfo(multiverseId: String): Single<LocalPrice>
    fun getAllShops(): Single<List<Shop>>
}