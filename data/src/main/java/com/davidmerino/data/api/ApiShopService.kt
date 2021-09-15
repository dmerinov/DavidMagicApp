package com.davidmerino.data.api

import com.davidmerino.data.model.shop.ShopResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiShopService {

    companion object {
        const val END_POINT = "https://llanosmunoz.com/"
    }

    @GET("cards.json")
    suspend fun getAllShops(): Response<ShopResponseDto>

}