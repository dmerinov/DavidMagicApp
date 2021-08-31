package com.davidmerino.data.datasource

import com.davidmerino.data.datasource.local.Local
import com.davidmerino.data.datasource.network.Network
import com.davidmerino.data.datasource.preferences.Preferences
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.model.LocalPrice
import com.davidmerino.domain.model.Shop
import com.davidmerino.domain.repository.Repository
import io.reactivex.Completable
import io.reactivex.Single

class CommonRepository(
    private val network: Network,
    private val local: Local,
    private val preferences: Preferences
) : Repository {

    override fun getCards(): Single<List<Card>> = network.getCards()

    override fun getCardByID(id: String) = local.getCardByID(id) // handle errors

    override fun getBoosterPack(expansion: String): Single<List<Card>> =
        network.getCardBooster(expansion)

    override fun getCardMarketInfo(multiverseId: String): Single<LocalPrice> =
        network.getCardMarketInfo(multiverseId)

    override fun getShops(): Single<List<Shop>> {
        val favouritesIDs = preferences.getFavourites()
        return network.getAllShops().map {
            return@map it.map {
                it.isFav = favouritesIDs.contains(it.id)
                return@map it
            }
        }
    }

    override fun setFavShop(shop: Shop): Completable {
        if (shop.isFav) {
            preferences.addFavouriteShop(shop.id)
        } else {
            preferences.removeFavouriteShop(shop.id)
        }
        return Completable.complete()
    }
}