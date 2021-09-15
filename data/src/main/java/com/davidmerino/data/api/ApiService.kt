package com.davidmerino.data.api

import com.davidmerino.data.model.card.cardResponseMTG.CardResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object {
        const val END_POINT = "https://api.magicthegathering.io/"
    }

    @GET("v1/cards")
    suspend fun getAllCards(): Response<CardResponseDto>

    @GET("v1/sets/{set}/booster")
    suspend fun mockBoosterPack(@Path("set") Set: String): Response<CardResponseDto>
}