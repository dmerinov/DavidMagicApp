package com.davidmerino.data.datasource.network

import com.davidmerino.data.api.ApiScryfallService
import com.davidmerino.data.api.ApiService
import com.davidmerino.data.mapper.toCard
import com.davidmerino.data.model.card.cardResponseMTG.CardResponseDto
import com.davidmerino.data.model.card.cardResponseScryfall.CardResponseScryfallDto
import com.davidmerino.domain.model.Card
import io.reactivex.Single

class NetworkDataSource(
    private val apiService: ApiService,
    private val apiScryfallService: ApiScryfallService
) : Network {

    override fun getCards(): Single<List<Card>> {
        return apiService.getAllCards().map { it.cards.map { it.toCard() } }
    }

    override fun getCardBooster(set: String): Single<List<Card>> {

        return apiService.mockBoosterPack(set).map{it.cards.map {it.toCard()}}
    }

    override fun getCardMarketInfo(multiverseId: String): Single<CardResponseScryfallDto> {
        return apiScryfallService.getCardInfo(multiverseId)
    }


}