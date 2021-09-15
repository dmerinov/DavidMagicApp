package com.davidmerino.data.datasource.network

import com.davidmerino.domain.Either
import com.davidmerino.domain.MagicError
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.model.LocalPrice
import com.davidmerino.domain.model.Shop

interface Network {
    suspend fun getCards(): Either<MagicError, List<Card>>
    suspend fun getCardBooster(set: String): Either<MagicError, List<Card>>
    suspend fun getCardMarketInfo(multiverseId: String): Either<MagicError, LocalPrice>
    suspend fun getAllShops(): Either<MagicError, List<Shop>>
}