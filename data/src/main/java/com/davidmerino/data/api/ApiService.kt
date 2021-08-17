package com.davidmerino.data.api

import com.davidmerino.data.model.card.CardResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("v1/cards")
    fun getAllCards(): Call<CardResponseDto>

    @GET("v1/sets/{set}/booster")
    fun mockBoosterPack(@Path("set") Set: String): Call<CardResponseDto>
}