package com.davidmerino.data.datasource.network

import com.davidmerino.data.api.ApiScryfallService
import com.davidmerino.data.api.ApiService
import com.davidmerino.data.mapper.toCard
import com.davidmerino.data.mapper.toLocalPrices
import com.davidmerino.data.model.card.cardResponseMTG.CardResponseDto
import com.davidmerino.data.model.card.cardResponseScryfall.CardResponseScryfallDto
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.model.LocalPrices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkDataSource(
    private val apiService: ApiService,
    private val apiScryfallService: ApiScryfallService
) : Network {

    override fun getCards(success: (List<Card>) -> Unit, error: () -> Unit) {
        val result: Call<CardResponseDto> = apiService.getAllCards()
        result.enqueue(object : Callback<CardResponseDto> {
            override fun onResponse(
                call: Call<CardResponseDto>,
                response: Response<CardResponseDto>
            ) {
                response.body()?.let { success(it.cards.map { it.toCard() }) }
            }

            override fun onFailure(call: Call<CardResponseDto>, t: Throwable) {
                error()
            }

        })
    }

    override fun getCardBooster(
        expansion: String,
        success: (List<Card>) -> Unit,
        error: () -> Unit
    ) {
        val result: Call<CardResponseDto> = apiService.mockBoosterPack(expansion)
        result.enqueue(object : Callback<CardResponseDto> {
            override fun onResponse(
                call: Call<CardResponseDto>,
                response: Response<CardResponseDto>
            ) {
                response.body()?.let { success(it.cards.map { it.toCard() }) }
            }

            override fun onFailure(call: Call<CardResponseDto>, t: Throwable) {
                error()
            }
        })
    }

    override fun getCardMarketInfo(
        multiverseId: String,
        success: (LocalPrices) -> Unit,
        error: () -> Unit
    ) {
        val result: Call<CardResponseScryfallDto> = apiScryfallService.getCardInfo(multiverseId)
        result.enqueue(object : Callback<CardResponseScryfallDto> {
            override fun onResponse(
                call: Call<CardResponseScryfallDto>,
                response: Response<CardResponseScryfallDto>
            ) {
                response.body()?.let { success(it.prices.toLocalPrices()) }
            }

            override fun onFailure(call: Call<CardResponseScryfallDto>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}