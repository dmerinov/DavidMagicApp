package com.davidmerino.data.api

import com.davidmerino.data.model.card.CardResponseDto
import com.davidmerino.data.model.set.SetResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("v1/cards")
    fun getAllCards(): Call<CardResponseDto>

    @GET("v1/sets")
    fun getAllSets(): Call<SetResponse>

}