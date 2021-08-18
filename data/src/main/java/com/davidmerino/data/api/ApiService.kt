package com.davidmerino.data.api

import com.davidmerino.data.model.card.cardResponseMTG.CardResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object {
        const val END_POINT = "https://api.magicthegathering.io/"
    }

    @GET("v1/cards")
    fun getAllCards(): Call<CardResponseDto>

    @GET("v1/sets/{set}/booster")
    fun mockBoosterPack(@Path("set") Set: String): Call<CardResponseDto>
}