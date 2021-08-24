package com.davidmerino.data.datasource

import com.davidmerino.data.datasource.local.Local
import com.davidmerino.data.datasource.network.Network
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.model.LocalPrices
import com.davidmerino.domain.repository.Repository
import io.reactivex.Single

class CommonRepository(private val network: Network, private val local: Local) : Repository {

    override fun getCards(): Single<List<Card>> = network.getCards()

    override fun getCardByID(id: String) = local.getCardByID(id) //controlar errores


    override fun getBoosterPack(
        expansion: String,
    ): Single<List<Card>> = network.getCardBooster(expansion)

    override fun getCardMarketInfo(multiverseId: String):
            Single<LocalPrices> = network.getCardMarketInfo(multiverseId)

}