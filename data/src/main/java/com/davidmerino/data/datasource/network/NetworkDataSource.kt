package com.davidmerino.data.datasource.network

import com.davidmerino.data.api.ApiClientBuilder
import com.davidmerino.data.api.ApiService
import com.davidmerino.data.model.card.CardDto
import com.davidmerino.data.model.card.CardResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkDataSource : Network {

    private val service: ApiService = ApiClientBuilder.getRetrofit().create(ApiService::class.java)

    override fun getCards(success: (List<CardDto>) -> Unit, error: () -> Unit) {

        val result: Call<CardResponseDto> = service.getAllCards()
        result.enqueue(object : Callback<CardResponseDto> {
            override fun onResponse(
                call: Call<CardResponseDto>,
                response: Response<CardResponseDto>
            ) {
                response.body()?.let { success(it.cards) }
            }

            override fun onFailure(call: Call<CardResponseDto>, t: Throwable) {
                error()
            }

        })

    }

}