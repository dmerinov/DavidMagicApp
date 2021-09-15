package com.davidmerino.data.api

import com.davidmerino.data.model.card.cardResponseScryfall.CardResponseScryfallDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiScryfallService {

    companion object {
        const val END_POINT = "https://api.scryfall.com/"
    }

    @GET("cards/multiverse/{multiverseId}")
    suspend fun getCardInfo(@Path("multiverseId") multiverseId: String): Response<CardResponseScryfallDto>

}