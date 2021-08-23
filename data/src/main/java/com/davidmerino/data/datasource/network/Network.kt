package com.davidmerino.data.datasource.network

import com.davidmerino.data.model.card.cardResponseMTG.CardResponseDto
import com.davidmerino.data.model.card.cardResponseScryfall.CardResponseScryfallDto
import com.davidmerino.domain.model.Card
import io.reactivex.Single

interface Network {
    fun getCards(): Single<List<Card>>
    fun getCardBooster(set: String): Single<CardResponseDto>
    fun getCardMarketInfo(multiverseId: String): Single<CardResponseScryfallDto>
}