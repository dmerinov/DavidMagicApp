package com.davidmerino.data.datasource

import com.davidmerino.data.datasource.cache.Cache
import com.davidmerino.data.datasource.local.Local
import com.davidmerino.data.datasource.network.Network
import com.davidmerino.data.datasource.preferences.Preferences
import com.davidmerino.domain.Either
import com.davidmerino.domain.MagicError
import com.davidmerino.domain.Success
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.model.LocalPrice
import com.davidmerino.domain.model.Shop
import com.davidmerino.domain.repository.Repository

class CommonRepository(
    private val network: Network,
    private val local: Local,
    private val preferences: Preferences,
    private val cache: Cache
) : Repository {

    override suspend fun getCards(): Either<MagicError, List<Card>> = network.getCards()

    override fun getCardByID(id: String): Either<MagicError, Card> = local.getCardByID(id)

    override suspend fun getBoosterPack(expansion: String): Either<MagicError, List<Card>> =
        network.getCardBooster(expansion)

    override suspend fun getCardMarketInfo(multiverseId: String): Either<MagicError, LocalPrice> =
        network.getCardMarketInfo(multiverseId)

    override suspend fun getShops(): Either<MagicError, List<Shop>> {
        val favouritesIDs = preferences.getFavourites()

        return when (val result = network.getAllShops()) {
            is Either.Left -> result
            is Either.Right -> Either.Right(
                result.success.map {
                    it.isFav = favouritesIDs.contains(it.id)
                    return@map it
                }
            )
        }
    }

    override fun setFavShop(shop: Shop): Either<MagicError, Success> {
        if (shop.isFav) {
            preferences.addFavouriteShop(shop.id)
        } else {
            preferences.removeFavouriteShop(shop.id)
        }
        return Either.Right(Success)
    }

    override fun setLifeCounter(player: Int, life: Int): Either<MagicError, Success> {
        cache.setLife(player, life)
        return Either.Right(Success)
    }

    override fun getLifeCounter(player: Int): Either<MagicError, Int> {
        return Either.Right(cache.getLife(player))
    }
}