package com.davidmerino.data.api

import com.davidmerino.data.model.card.cardResponseScryfall.CardResponseScryfallDto
import retrofit2.Call
import retrofit2.http.GET

interface ApiScryfallService {

    companion object {
        const val END_POINT = "https://api.scryfall.com/"
    }

    @GET("cards/cardmarket/{multiverseId}")
    fun getCardInfo(multiverseId: String): Call<CardResponseScryfallDto>

}