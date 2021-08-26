package com.davidmerino.data.datasource.network

import com.davidmerino.data.api.ApiScryfallService
import com.davidmerino.data.api.ApiService
import com.davidmerino.data.api.ApiShopService
import com.davidmerino.data.mapper.toCard
import com.davidmerino.data.mapper.toLocalPrices
import com.davidmerino.data.mapper.toLocations
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.model.LocalPrice
import com.davidmerino.domain.model.Location
import io.reactivex.Single

class NetworkDataSource(
    private val apiService: ApiService,
    private val apiScryfallService: ApiScryfallService,
    private val apiShopService: ApiShopService
) : Network {

    override fun getCards(): Single<List<Card>> {
        return apiService.getAllCards().map { it.cards.map { it.toCard() } }
    }

    override fun getCardBooster(set: String): Single<List<Card>> {

        return apiService.mockBoosterPack(set).map { it.cards.map { it.toCard() } }
    }

    override fun getCardMarketInfo(multiverseId: String): Single<LocalPrice> {
        return apiScryfallService.getCardInfo(multiverseId).map { it.prices.toLocalPrices() }
    }

    override fun getAllShops(): Single<List<Location>> {
        return apiShopService.getAllShops().map { it.sites.map { it.toLocations() } }
    }


}